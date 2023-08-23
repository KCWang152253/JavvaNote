package shangyin;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/8/8 下午3:04
 *
 *      C语言 分期函数后缀 txn
 *      老系统路由：Bridge->switch->stub->服务
 *      分期开发文档
 *          1.授信、用信
 *
 *
 *      旧代码：正常的分期信息存放 pln_req、pln_dtl
 *             直客式分期：额度外分期  把钱直接给到客户 比如专项分期 类似中行 自主支付
 *             单笔单批：把钱直接给到商户去使用  类似中行 受托支付
 *             专项分期：先存放lim_req和lim_dtl再转存pln_req和pln_dtl
 *             专项：受托 划转 有一个回盘文件判断是否转账成功
 *                  个人 划转
 *                  刷卡分期->oam_pimtxn->划转  联机
 *
 *
 */
public class Document {
}
