// 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
//
// 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。 
//
// 
//
// 示例 1: 
//
// 
// 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
// 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
//
// 示例 2: 
//
// 
// 输入: strs = [""]
// 输出: [[""]]
// 
//
// 示例 3: 
//
// 
// 输入: strs = ["a"]
// 输出: [["a"]]
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 10⁴ 
// 0 <= strs[i].length <= 100 
// strs[i] 仅包含小写字母 
// 
//
// Related Topics 数组 哈希表 字符串 排序 👍 2082 👎 0


package com.wanglj.wanglj2022.study.algorithm;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字母异位词分组
 * @author Wanglj
 * @date 2024-12-26 16:19:19
 */
public class P49_GroupAnagrams {
    public static void main(String[] args) {
		String[] strings = new String[]{"eat", "tea", "tan", "ate", "nat", "bat","beau","eabu"};
		// String[] strings = new String[]{"a"};
		// 测试代码
        Solution solution = new P49_GroupAnagrams().new Solution();
		List<List<String>> lists = solution.groupAnagrams(strings);
		System.out.println(JSONUtil.toJsonStr(lists));
	}

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<String>> groupAnagrams(String[] strs) {
            Map<Object, List<String>> map = new HashMap<>();
            for (String str : strs) {
                String sortStr = this.sortStr(str);
                int hashCode = sortStr.hashCode();
                List<String> stringList = map.getOrDefault(hashCode, new ArrayList<>());
                stringList.add(str);
                map.put(hashCode, stringList);
            }
            List<List<String>> list = new ArrayList<List<String>>();
            for (Map.Entry<Object, List<String>> objectListEntry : map.entrySet()) {
                list.add(objectListEntry.getValue());
            }
            return list;
        }

        private String sortStr(String str) {
            String[] arr = str.split("");
            int len = arr.length;
            if (len < 2) return str;
            int minVal =  Integer.valueOf('a');
            int[] countArr = new int[Integer.valueOf('z') - Integer.valueOf('a') + 1];
            for (String val : arr) {
                countArr[val.charAt(0) - minVal]++;
            }
            for (int arrIdx = 0, countIdx = 0; countIdx < countArr.length; countIdx++) {
                while (countArr[countIdx]-- > 0) {
                    arr[arrIdx++] = String.valueOf((char) (minVal + countIdx));
                }
            }
			StringBuilder sortStr = new StringBuilder();
			for (String s : arr) {
				sortStr.append(s);
			}
			return sortStr.toString();
        }

    }
// leetcode submit region end(Prohibit modification and deletion)

}
