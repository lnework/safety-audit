package hust.software.elon.config;

import hust.software.elon.constant.MiddleConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elon
 * @date 2022/3/30 16:40
 */
@Configuration
public class ConfigBeanConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "file")
    public FileProperties fileProperties(){
        FileProperties fileProperties = new FileProperties();
        setRootBySystem(fileProperties);
        return fileProperties;
    }

    public void setRootBySystem(FileProperties fileProperties){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith(MiddleConstant.WIN)) {
            fileProperties.setSystemKind(FileProperties.WIN_CODE);
        } else if(os.toLowerCase().startsWith(MiddleConstant.MAC)){
            fileProperties.setSystemKind(FileProperties.MAC_CODE);
        }
    }
}
