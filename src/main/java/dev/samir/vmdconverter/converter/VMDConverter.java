package dev.samir.vmdconverter.converter;


import dev.samir.vmdconverter.AppController;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class VMDConverter {

    public static void convertVideoMP4(String inputFilePath, String outputFilePath) {
        String input_file = inputFilePath;
        String output_file = "\"" + outputFilePath + "/" + AppController.getFilename() + ".mp4\"";

        String command = String.format("ffmpeg -i \"%s\" -codec:d vmdvideo -preset slow -crf 20 -codec:a aac -b:a 128k -movflags +faststart %s", input_file, output_file);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("FFmpeg command execution completed with exit code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void convertAudioMP3(String inputFilePath, String outputFilePath) {
        String input_file = inputFilePath;
        String output_file = "\"" + outputFilePath + "/" + AppController.getFilename() + ".mp3\"";

        String command = String.format("ffmpeg -i \"%s\" -vn -codec:a libmp3lame -qscale:a 2 %s", input_file, output_file);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("FFmpeg command execution completed with exit code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}