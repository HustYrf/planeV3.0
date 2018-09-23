package hust.plane.service.interFace;

import java.util.List;

public interface UserGroupService {
    List<Integer> selectGroupIdWithUserId(int id);

    int updateAuthorityWithUserId(Integer id, List<String> authorityList);
}
