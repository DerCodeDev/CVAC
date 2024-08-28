import java.io.*;

import de.samir.vmdconverter.AppController;

public class STBConverter {
    public static void convertAudioMP3toOGG(String inputFilePath, String outputDirectory) {
        // Extract the filename without extension
        String filename = new File(inputFilePath).getName();
        String baseFilename = filename.substring(0, filename.lastIndexOf('.'));

        // Define the output file paths
        String outputOGGFilePath = outputDirectory + File.separator + baseFilename + ".ogg";
        String outputSTBFilePath = outputDirectory + File.separator + baseFilename + ".stb";

        // FFmpeg command to convert MP3 to OGG
        String command = String.format("ffmpeg -i %s -vn -codec:a libvorbis -qscale:a 2 %s", inputFilePath, outputOGGFilePath);

        try {
            // Execute the FFmpeg command
            ProcessBuilder processBuilder = new ProcessBuilder(command.split("\\s+"));
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Read and print the FFmpeg output
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("FFmpeg command execution completed with exit code: " + exitCode);

            // Rename the OGG file to STB
            File oggFile = new File(outputOGGFilePath);
            File stbFile = new File(outputSTBFilePath);
            if (oggFile.renameTo(stbFile)) {
                System.out.println("File renamed to: " + stbFile.getAbsolutePath());
            } else {
                System.out.println("Failed to rename file.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
