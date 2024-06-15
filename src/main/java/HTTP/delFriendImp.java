package HTTP;

/**
 * 通过姓名删除好友接口
 */
public interface delFriendImp
{
    /**
     * 通过姓名删除好友
     * @param friendName 好友姓名
     * @return 删除成功返回true，否则返回false
     */
    public boolean delFriendByName(String friendName);
}
