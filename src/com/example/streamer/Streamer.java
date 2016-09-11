package com.example.streamer;

import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

class Streamer {

AudioInputStream audioInputStream;
static AudioInputStream ais;
static AudioFormat format;
static boolean status = true;
static int port = 50005;
static int sampleRate = 44100;
static int bufsize = 8192;
public static void main(String args[]) throws Exception {
	if(args.length==2){
	try{bufsize = Integer.parseInt(args[0]);} catch(Exception e){System.out.println("wrong bufsize!");return;}
	try{port = Integer.parseInt(args[1]);} catch(Exception e){System.out.println("wrong port!");return;}
    DatagramSocket serverSocket = new DatagramSocket(port);
    DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
    SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);

    byte[] receiveData = new byte[bufsize]; 
    // ( 1280 for 16 000Hz and 3584 for 44 100Hz (use AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat) to get the correct size)

    format = new AudioFormat(sampleRate, 16, 1, true, false);

    while (status == true) {
        DatagramPacket receivePacket = new DatagramPacket(receiveData,
                receiveData.length);
        serverSocket.receive(receivePacket);
        ByteArrayInputStream baiss = new ByteArrayInputStream(
                receivePacket.getData());
        ais = new AudioInputStream(baiss, format, receivePacket.getLength());
        // A thread solve the problem of chunky audio 
        new Thread(new Runnable() {
            @Override
            public void run() {
                toSpeaker(receivePacket.getData(),sourceDataLine);
            }
        }).start();
    }
    serverSocket.close();
    }
	else{
		System.out.println("usage this.jar bufsize port");
	}
}

public static void toSpeaker(byte soundbytes[],SourceDataLine sourceDataLine) {
    try {
        sourceDataLine.open(format);
        FloatControl volumeControl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(volumeControl.getMaximum());
        sourceDataLine.start();
        sourceDataLine.open(format);
        sourceDataLine.start();
        sourceDataLine.write(soundbytes, 0, soundbytes.length);
        sourceDataLine.drain();
        sourceDataLine.close();
    } catch (Exception e) {
        System.out.println("Not working in speakers...");
        e.printStackTrace();
    }
}
}