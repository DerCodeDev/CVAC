package de.samir.vmdconverter.converter;


import de.samir.vmdconverter.AppController;

import java.io.*;

public class VMDConverter {

    public static void convertVideoMP4(String inputFilePath, String outputFilePath) {
        String output_file = outputFilePath + File.separator + AppController.getFilename() + ".mp4";

        String command = String.format("ffmpeg -i %s -codec:d vmdvideo -preset slow -crf 20 -codec:a aac -b:a 128k -movflags +faststart %s", inputFilePath, output_file);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
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
        String output_file = outputFilePath + File.separator + AppController.getFilename() + ".mp3";

        String command = String.format("ffmpeg -i  %s -vn -codec:a libmp3lame -qscale:a 2 %s", inputFilePath, output_file);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
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

    public static void convertAudioWAV22050(String inputFilePath, String outputFilePath) {
        String output_file = outputFilePath + File.separator + AppController.getFilename() + ".wav";

        String command = String.format("ffmpeg -i %s -acodec pcm_s16le -ac 1 -ar 22050 %s", inputFilePath, output_file);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s"));
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

        public static void convertPictureWEBM(String inputFilePath, String outputFilePath) {
        String output_file = outputFilePath + File.separator + AppController.getFilename() + ".webm";

        String command = String.format("ffmpeg -i  %s -vn -codec:a libopus -qscale:a 2 %s", inputFilePath, output_file);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
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

        public static void convertPicturePNG(String inputFilePath, String outputFilePath) {
        String output_file = outputFilePath + File.separator + AppController.getFilename() + ".png";

        String command = String.format("ffmpeg -i  %s -filter_complex "showwavespic" -frames:v 1 %s", inputFilePath, output_file);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
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

    public static void convertAudioWAV44100(String inputFilePath, String outputFilePath) {
        String output_file = outputFilePath + File.separator + AppController.getFilename() + ".wav";

        String command = String.format("ffmpeg -i %s -acodec pcm_s16le -ac 1 -ar 44100 %s", inputFilePath, output_file);

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s"));
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