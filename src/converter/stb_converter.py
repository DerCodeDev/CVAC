# stb_converter.py

import subprocess
import os

def convert_stb_to_ogg(input_file_path, output_directory_path):
    """
    Convert STB files to OGG format.

    Args:
        input_file_path (str): The path to the input STB file.
        output_directory_path (str): The path to the output directory where the converted OGG file will be saved.
    """
    # Ensure output directory exists
    os.makedirs(output_directory_path, exist_ok=True)

    # Set output file path
    output_file = os.path.join(output_directory_path, os.path.splitext(os.path.basename(input_file_path))[0] + ".ogg")

    # FFmpeg command to convert STB to OGG
    command = f"ffmpeg -i {input_file_path} -c:a libvorbis -q:a 4 {output_file}"

    # Execute FFmpeg command
    try:
        subprocess.run(command, shell=True, check=True)
        print("STB to OGG conversion completed.")
    except subprocess.CalledProcessError as e:
        print("Error occurred during STB to OGG conversion:", e)
