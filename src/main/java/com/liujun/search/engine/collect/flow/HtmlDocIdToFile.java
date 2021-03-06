package com.liujun.search.engine.collect.flow;

import com.liujun.search.common.flow.FlowServiceContext;
import com.liujun.search.common.flow.FlowServiceInf;
import com.liujun.search.engine.collect.constant.CollectAnalyzeFlowKeyEnum;
import com.liujun.search.engine.collect.operation.DocIdproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 进行网页id的存储存储到文件的操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/17
 */
public class HtmlDocIdToFile implements FlowServiceInf {

  /** 实例对象 */
  public static final HtmlDocIdToFile INSTANCE = new HtmlDocIdToFile();

  private Logger logger = LoggerFactory.getLogger(HtmlDocIdToFile.class);

  @Override
  public boolean runFlow(FlowServiceContext context) throws Exception {

    logger.info("collect docid to file start");

    // 1,获取当前网页链接
    String urlAddress = context.getObject(CollectAnalyzeFlowKeyEnum.FLOW_DOWNLOAD_ADDRESS.getKey());

    // 2,获取当前网页的id
    long numberId = context.getObject(CollectAnalyzeFlowKeyEnum.FLOW_CONTEXT_NUMBER_SEQID.getKey());

    // 3,进行将网页id的存储操作
    DocIdproc.INSTANCE.putDoc(urlAddress, numberId);

    // 写入磁盘操作
    DocIdproc.INSTANCE.writeDisk();

    logger.info("collect docid to file finish");
    return true;
  }
}
