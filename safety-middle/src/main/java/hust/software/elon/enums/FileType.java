package hust.software.elon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * @author elon
 * @date 2022/4/1 14:52
 */
@Getter
@AllArgsConstructor
public enum  FileType {
    Audio(1, "音频文件"),
    Other(0, "其他文件"),
    ;

    private static final List<FileType> FileTypeList = Arrays.asList(FileType.Audio, FileType.Other);



    private final Integer type;
    private final String name;

    public static FileType getEnums(int type){
        for (FileType fileType: FileTypeList){
            if (fileType.getType() == type)
                return fileType;
        }
        return Other;
    }
}
