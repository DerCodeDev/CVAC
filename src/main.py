import tkinter as tk
from tkinter import filedialog, messagebox, simpledialog
from converter.vmd_converter import convert_video_mp4, convert_audio_mp3, convert_audio_wav
from converter.stb_converter import convert_stb_to_ogg, convert_audio_to_ogg

class CVACApp(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("CVAC 1.5.0")

        self.input_file_path = ""
        self.output_directory_path = ""
        self.selected_option = tk.StringVar(value="Video to MP4")

        # Configure grid weights
        self.columnconfigure(0, weight=1)
        self.rowconfigure(1, weight=1)

        # Input file selection
        input_frame = tk.Frame(self)
        input_frame.grid(row=0, column=0, padx=10, pady=5, sticky="ew")
        input_frame.columnconfigure(0, weight=1)
        input_label = tk.Label(input_frame, text="Input File:")
        input_label.grid(row=0, column=0, padx=(0, 5), pady=5, sticky="e")
        self.input_entry = tk.Entry(input_frame, width=40)
        self.input_entry.grid(row=0, column=1, padx=(0, 5), pady=5, sticky="ew")
        input_button = tk.Button(input_frame, text="Browse", command=self.select_input_file)
        input_button.grid(row=0, column=2, padx=(0, 5), pady=5)

        # Output directory selection
        output_frame = tk.Frame(self)
        output_frame.grid(row=1, column=0, padx=10, pady=5, sticky="ew")
        output_frame.columnconfigure(0, weight=1)
        output_label = tk.Label(output_frame, text="Output Directory:")
        output_label.grid(row=0, column=0, padx=(0, 5), pady=5, sticky="e")
        self.output_entry = tk.Entry(output_frame, width=40)
        self.output_entry.grid(row=0, column=1, padx=(0, 5), pady=5, sticky="ew")
        output_button = tk.Button(output_frame, text="Browse", command=self.select_output_directory)
        output_button.grid(row=0, column=2, padx=(0, 5), pady=5)

        # Conversion option selection
        option_frame = tk.Frame(self)
        option_frame.grid(row=2, column=0, padx=10, pady=5, sticky="ew")
        option_frame.columnconfigure(0, weight=1)
        option_label = tk.Label(option_frame, text="Conversion Option:")
        option_label.grid(row=0, column=0, padx=(0, 5), pady=5, sticky="e")
        self.option_menu = tk.OptionMenu(option_frame, self.selected_option, "Video to MP4", "Audio to MP3", "Audio to WAV", "STB to OGG", command=self.option_changed)
        self.option_menu.grid(row=0, column=1, padx=(0, 5), pady=5, sticky="ew")

        # Convert button
        self.convert_button = tk.Button(self, text="Convert", command=self.convert)
        self.convert_button.grid(row=3, column=0, padx=10, pady=10)

    def select_input_file(self):
        file_types = [("Video and Media Data", "*.vmd"), ("STB Files", "*.stb"), ("DAT Files", "*.dat")]
        self.input_file_path = filedialog.askopenfilename(filetypes=file_types)
        self.input_entry.delete(0, tk.END)
        self.input_entry.insert(0, self.input_file_path)

    def select_output_directory(self):
        self.output_directory_path = filedialog.askdirectory()
        self.output_entry.delete(0, tk.END)
        self.output_entry.insert(0, self.output_directory_path)

    def option_changed(self, *args):
        if self.selected_option.get() == "STB to OGG":
            self.convert_button.config(command=self.convert_stb_to_ogg)
        elif self.selected_option.get() == "Audio to OGG":
            self.convert_button.config(command=self.convert_audio_to_ogg)
        else:
            self.convert_button.config(command=self.convert)

    def convert(self):
        if not self.input_file_path or not self.output_directory_path:
            messagebox.showerror("Error", "Please select input file and output directory.")
            return

        if self.selected_option.get() == "Video to MP4":
            convert_video_mp4(self.input_file_path, self.output_directory_path)
        elif self.selected_option.get() == "Audio to MP3":
            convert_audio_mp3(self.input_file_path, self.output_directory_path)
        elif self.selected_option.get() == "Audio to WAV":
            sample_rate = simpledialog.askinteger("Sample Rate", "Enter the sample rate for WAV conversion:")
            if sample_rate is not None:
                convert_audio_wav(self.input_file_path, self.output_directory_path, sample_rate)
            else:
                messagebox.showerror("Error", "Please enter a valid sample rate.")
                return

        messagebox.showinfo("Conversion", "Conversion completed.")

    def convert_stb_to_ogg(self):
        if not self.input_file_path or not self.output_directory_path:
            messagebox.showerror("Error", "Please select input file and output directory.")
            return

        convert_stb_to_ogg(self.input_file_path, self.output_directory_path)
        messagebox.showinfo("Conversion", "STB to OGG Conversion completed.")

    def convert_audio_to_ogg(self):
        if not self.input_file_path or not self.output_directory_path:
            messagebox.showerror("Error", "Please select input file and output directory.")
            return

        convert_audio_to_ogg(self.input_file_path, self.output_directory_path)
        messagebox.showinfo("Conversion", "Audio to OGG Conversion completed.")

if __name__ == "__main__":
    app = CVACApp()
    app.mainloop()
