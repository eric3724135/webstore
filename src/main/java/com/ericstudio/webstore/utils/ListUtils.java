package com.ericstudio.webstore.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ericstudio.webstore.domain.KaoBus;

public class ListUtils {
	
	private static List<KaoBus> kaoBusList = new ArrayList<KaoBus>();
	
	/*
	 * BigDecimal.ROUND_CEILING　正數無條件進入,負數無條件捨去 BigDecimal.ROUND_DOWN 　無條件捨去到
	 * scale 位 BigDecimal.ROUND_FLOOR　正數無條件捨去，負數無條件進入
	 * BigDecimal.ROUND_HALF_DOWN　四捨五捨六入
	 * BigDecimal.ROUND_HALF_EVEN　四捨六入,五入捨後該scale位數值必需為偶數
	 * BigDecimal.ROUND_HALF_UP　四捨五入 BigDecimal.ROUND_UP　無條件進入到 scale 位
	 */
	/**
	 * @param perPage
	 *            每頁顯示幾筆資料
	 * @param pageIndex
	 *            目前頁面索引
	 * @param total
	 *            總比數
	 * @return 回傳sublist index
	 */
	public static int[] getSublistRange(BigDecimal perPage,
			BigDecimal pageIndex, BigDecimal total) {
		int[] result = new int[2];
		@SuppressWarnings("static-access")
		int totalPage = total.divide(perPage).ROUND_CEILING;
		result[0] = pageIndex.multiply(perPage.subtract(new BigDecimal(1)))
				.intValue();
		if (pageIndex.equals(totalPage)) {
			result[1] = total.subtract(new BigDecimal(1)).intValue();
		} else {

			result[1] = perPage.add(new BigDecimal(result[0])).intValue();
		}

		return result;
	}

	public static List<KaoBus> getKaoBusList() {
		return kaoBusList;
	}

	public static void setKaoBusList(List<KaoBus> kaoBusList) {
		ListUtils.kaoBusList = kaoBusList;
	}
	
		
	
}
