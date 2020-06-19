package com.ucap.zfw.util;
import com.ucap.restful.client.ResClientUtils;
public class ManuScriptTest {
	/**
	 * 新增稿件
	 * @param ms_q
	 *//*
	public void manuScriptSave(ManuScript ms_q) {
		//List<ManuScript> list = null;
		try {
			list = msd.selectManuScript(ms_q);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			ManuScript ms = list.get(i);
			Map<String, String> map = new HashMap<String, String>();
			map.put("title", ms.getDocTitle());
			map.put("subTitle", ms.getDocTitle());
			if (ms.getDocHtmlCon() != null && !"".equals(ms.getDocHtmlCon())) {
				String content = ms.getDocHtmlCon();
				String puburl = ms.getDocPubUrl();
				puburl = puburl.replaceAll("www.cpfla.org.cn", "www.jianhun.org.cn");
				content = HtmlUtil.srcReplacePath(content, puburl);
				content = HtmlUtil.hrefReplacePath(content, puburl);
				map.put("content", content);
			} else {
				map.put("content", "<p></p>");
			}
			
			map.put("keyword", "");
			map.put("memo", "");
			map.put("channelId", ms_q.getS_channlid());
			map.put("websiteId", ms_q.getS_webid());
			map.put("userId", ms_q.getS_creatorid());
			map.put("isPublish", "true");
			map.put("publishedTime", ms.getDocRelTime());
			map.put("createdTime", ms.getDocRelTime());
			*//**
			 * 元数据
			 *//*
			//作者
			map.put("meta_zuozhe", ms.getDocAuthor());
			//来源
			map.put("meta_laiyuan", ms.getDocSourceName());
			//责任编辑
			map.put("meta_zrbj", ms.getNickName());
			
			*//**
			 * 添加相关附件
			 *//*
			if (ms.getDocId() != null && !"".equals(ms.getDocId())) {
				Appendix ad = new Appendix();
				ad.setQ_appdocid(ms.getDocId());
				try {
					List<Appendix> appendixList = msd.selectFile(ad);
					if (appendixList.size() > 0) {
						List<JSONObject> listJson = new ArrayList<JSONObject>();
						for (int j = 0; j < appendixList.size(); j++) {
							JSONObject json = ft.cmsUpLoadFile(appendixList.get(j).getPubUrl(), appendixList.get(j).getAppFile());
							if (json != null) {
								listJson.add(json);
								map.put("attachment", JSONArray.fromObject(listJson).toString());
							}
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			*//**
			 * TYPE = 40
			 *//*
			
			 *  List<JSONObject> listJson = new ArrayList<JSONObject>();
				listJson.add(ft.cmsUpLoadFile(ms.getDocPubUrl(), ms.getDocPubUrl().substring(ms.getDocPubUrl().lastIndexOf("/")+1)));
				map.put("attachment", JSONArray.fromObject(listJson).toString());
			
			*//**
			 * 外链
			 *//*
			//map.put("redirectUrl", ms.getDocPubUrl());
			
			String msg = ResClientUtils.sendPostManuscriptUrl(ms_q.getS_cmsurl(),ms_q.getS_strategycode(), ms_q.getS_key(), map);
			System.out.println(msg);
		}
		
	}*/
}
