package hust.software.elon.util;


import hust.software.elon.domain.KeyWord;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 关键词尾节点
 * 
 * @author hailin0@yeah.net
 * @createDate 2016年5月22日
 *
 */
public class EndTagUtil implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8278503553932163596L;

    /**
     * 尾节点key
     */
    public static final String TREE_END_TAG = "end";

    public static Map<String, Map> buind(KeyWord KeyWord) {
        Map<String, Map> endTag = new HashMap<String, Map>();
        endTag.put(TREE_END_TAG, new HashMap<String, String>());
        return endTag;
    }

}
