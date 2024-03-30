import subprocess
import os

def convert_video_mp4(input_file_path, output_file_path):
    output_file = os.path.join(output_file_path, "output.mp4")
    command = [
        "ffmpeg",
        "-i", input_file_path,
        "-codec:d", "vmdvideo",
        "-preset", "slow",
        "-crf", "20",
        "-codec:a", "aac",
        "-b:a", "128k",
        "-movflags", "+faststart",
        output_file
    ]
    _execute_ffmpeg_command(command)

def convert_audio_mp3(input_file_path, output_file_path):
    output_file = os.path.join(output_file_path, "output.mp3")
    command = [
        "ffmpeg",
        "-i", input_file_path,
        "-vn",
        "-codec:a", "libmp3lame",
        "-qscale:a", "2",
        output_file
    ]
    _execute_ffmpeg_command(command)

def convert_audio_wav(input_file_path, output_file_path, sample_rate):
    output_file = os.path.join(output_file_path, "output.wav")
    command = [
        "ffmpeg",
        "-i", input_file_path,
        "-acodec", "pcm_s16le",
        "-ac", "1",
        "-ar", str(sample_rate),
        output_file
    ]
    _execute_ffmpeg_command(command)

def _execute_ffmpeg_command(command):
    try:
        process = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, universal_newlines=True)
        for line in process.stdout:
            print(line.strip())
        process.wait()
        print("FFmpeg command execution completed with exit code:", process.returncode)
    except subprocess.CalledProcessError as e:
        print("Error executing FFmpeg command:", e)
