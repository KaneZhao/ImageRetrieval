package com.hbd.retrieval.common.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class UniqueMap {

	/**
	 * 由于多特征可能会检索到同一张图片，结果中会有重复的，但只需要返回一个。
	 * 删除得分小的。（得分表示相似度）
	 * @param map 检索获得的map
	 * @return
	 */
	public static Map<Integer, String> removeRepetitionFromMap(
			Map<Integer, String> map) {

		Set<Entry<Integer, String>> set = map.entrySet();
		List<Entry<Integer, String>> list = new ArrayList<Entry<Integer, String>>(
				set);

		Collections.sort(list, new Comparator<Entry<Integer, String>>() {
			public int compare(Entry<Integer, String> entry1,
					Entry<Integer, String> entry2) {
				return Integer.valueOf(entry1.getValue().hashCode())
						- Integer.valueOf(entry2.getValue().hashCode());
			}
		});

		// list.size() is dynamic change
		for (int index = 0; index < list.size(); index++) {
			Integer key = list.get(index).getKey();
			String value = list.get(index).getValue();

			int next_index = index + 1;

			if (next_index < list.size()) {
				Integer next_key = list.get(next_index).getKey();
				String next_value = list.get(next_index).getValue();
				// remove repetition record whose key is more smaller
				if (value.equals(next_value)) {
					if (key.hashCode() > next_key.hashCode()) {
						map.remove(next_key);
						list.remove(next_index);
					} else {
						map.remove(key);
						list.remove(index);
					}

					// Due to hasing repetiton in list,so index will be reduced
					index--;
				}
			}
		}
		return map;
	}

	/**
	 * 按分值排序
	 * @param map
	 * @return
	 */
	public static Map<Integer, String> transferToSortedMap(
			Map<Integer, String> map) {
		// Define comparator for TreeMap
		Map<Integer, String> new_sort_map = new TreeMap<Integer, String>(
				new Comparator<Integer>() {
					public int compare(Integer key1, Integer key2) {

						return -(key1.hashCode() - key2.hashCode());
					}
				});

		new_sort_map.putAll(map);
		return new_sort_map;
	}

	/**
	 * 打印结果
	 * @param map
	 */
	public static void printMap(Map<Integer, String> map) {
		Iterator<Entry<Integer, String>> iterator = map.entrySet().iterator();

		while (iterator.hasNext()) {
			// Entry<Integer, String> entry = iterator.next();

			// Integer key = entry.getKey();
			// String value = entry.getValue();

			// //获取格式化对象
			// NumberFormat nt = NumberFormat.getPercentInstance();
			//
			// //设置百分数精确度1即保留一位小数
			// nt.setMinimumFractionDigits(1);

			// 最后格式化并输出
			// System.out.println(nt.format(key)+ "-->" + value);
		}
	}

	/**
	 *  遍历map获取检索结果图像路径
	 * @param map
	 * @return
	 */
	public static String[] getResultsFromMap(Map<Float, String> map) {
		Iterator<Entry<Float, String>> iterator = map.entrySet().iterator();
		String[] results = new String[map.size()];
		int i = 0;
		while (iterator.hasNext()) {
			Entry<Float, String> entry = iterator.next();
			String value = entry.getValue();
			results[i++] = value;
		}
		return results;
	}

	/**
	 *  获取结果图像的相似百分比
	 * @param map
	 * @return
	 */
	public static String[] getKeyFromMap(Map<Float, String> map) {
		Iterator<Entry<Float, String>> iterator = map.entrySet().iterator();
		String[] keyResults = new String[map.size()];
		int i = 0;
		while (iterator.hasNext()) {
			Entry<Float, String> entry = iterator.next();
			Float key = entry.getKey();
			NumberFormat nt = NumberFormat.getPercentInstance();
			nt.setMinimumFractionDigits(1);
			keyResults[i++] = nt.format(key);
			// System.out.println(nt.format(key));
		}
		return keyResults;
	}
}

	
	

