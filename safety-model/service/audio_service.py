import os.path

import requests


class AudioService():

    def __init__(self, download_audio_url: str, query_audio_directory: str, audio_postfix: str):
        self.download_audio_url = download_audio_url
        self.query_audio_directory = query_audio_directory
        self.audio_postfix = audio_postfix

    def download_query_audio(self, audio_id: int):
        download_url = self.download_audio_url + str(audio_id)
        audio_file = requests.get(download_url, allow_redirects=True)
        file_path = self.query_audio_directory + "/" + str(audio_id) + self.audio_postfix
        if not os.path.exists(file_path):
            open(file_path, "wb").write(audio_file.content)
        return file_path

    def download_seed_audio(self, audio_id: int, seed_audio_directory: str):
        if seed_audio_directory is not None:
            download_url = self.download_audio_url + str(audio_id)
            audio_file = requests.get(download_url, allow_redirects=True)
            file_path = seed_audio_directory + "/" + str(audio_id) + self.audio_postfix
            open(file_path, "wb").write(audio_file.content)


if __name__ == '__main__':
    audio_service = AudioService("http://127.0.0.1:7777/file/download-big?fileId=", "../query_audio_data", ".wav")
    audio_service.download_query_audio(17)
