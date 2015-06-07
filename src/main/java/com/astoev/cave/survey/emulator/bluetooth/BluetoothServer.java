package com.astoev.cave.survey.emulator.bluetooth;

/**
 * Created by astoev on 6/2/15.
 */
public class BluetoothServer {

    public static void start(String aDevice) {

        // suse config
//        hciconfig -a
//        hciconfig hci0 name TEST
        // start server and connect


        new Thread( new WaitThread()).start();
       /* try {
            System.out.println("Starting " + aDevice);

            //Create a UUID for SPP
            UUID uuid = new UUID("00001101-0000-1000-8000-00805F9B34FB", true);
            //Create the servicve url
            String connectionString = "btspp://localhost:" + uuid +";name=Sample SPP Server";

            //open server url
            StreamConnectionNotifier streamConnNotifier = (StreamConnectionNotifier) Connector.open(connectionString);

            //Wait for client connection
            System.out.println("\nServer Started. Waiting for clients to connect...");
            StreamConnection connection=streamConnNotifier.acceptAndOpen();
            RemoteDevice dev = RemoteDevice.getRemoteDevice(connection);
*//*

        //read string from spp client
        InputStream inStream=connection.openInputStream();
        BufferedReader bReader=new BufferedReader(new InputStreamReader(inStream));
        String lineRead=bReader.readLine();
        System.out.println(lineRead);

        //send response to spp client
        OutputStream outStream=connection.openOutputStream();
        BufferedWriter bWriter=new BufferedWriter(new OutputStreamWriter(outStream));
        bWriter.write("Response String from SPP Server\r\n");*//*
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public static void stop() {
        System.out.println("Stop server");
    }

}


