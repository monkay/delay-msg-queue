# delay-msg-queue
简易延迟消息队列实现

## 要点
* 组成一个环形队列，默认3600个元素，每个元素包含一个列表，列表中包含需要执行的任务
* 定时任务每秒扫描队列中的一个队列，currentIndex++，循环一圈一个小时
* cycleNum 为 0 时，表示可以执行，不为 0 减 1
* 根据任务需要延迟的时间，计算 cycleNum 和 任务放置的队列
