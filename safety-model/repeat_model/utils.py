# encoding:utf-8
import os
import time

import numpy as np
import matplotlib.pyplot as plt

"""辅助函数
"""

def is_exist(path:str):
    if not os.path.exists(path):
        os.makedirs(path)

def frame_to_second(frame:int, frame_length:int=400, hop_length:int=200, sr:int=16000):
    # 帧转秒
    return (frame * hop_length + frame_length / 2) / sr

def second_to_frame(second:float, frame_length:int=400, hop_length:int=200, sr:int=16000):
    # 秒转帧
    if second <= 0:
        return 0
    return int((second * sr - frame_length / 2) / hop_length)

def save_hist_match(match_pairs:list, match_start_end_point:list, name:str):
    #可视化匹配对,45度线并保存
    plt.figure(figsize=(30,10))
    ax2 = plt.subplot(1, 2, 1)
    plt.title("2. match_pairs")
    plt.scatter(match_pairs[:,0], match_pairs[:,1], s=10)
    plt.xlabel("origin audio/frame")
    plt.ylabel("target audio/frame")

    if match_start_end_point.size > 0:
        ax3 = plt.subplot(1, 2, 2)
        plt.title("3. match_start_end_point")
        lines=np.zeros(220)+5
        plt.scatter(match_pairs[:,0], match_pairs[:,1], s=10)    
        plt.scatter(match_start_end_point[:,0], match_start_end_point[:,1], marker="x", c="red", linewidths=lines)
        plt.xlabel("origin audio/frame")
        plt.ylabel("target audio/frame")

    picture_folder = "picture_data"
    is_exist(picture_folder)
    save_plot_path = os.path.join(picture_folder, f"{name}_hist_match.png")
    plt.savefig(save_plot_path, dpi=400)