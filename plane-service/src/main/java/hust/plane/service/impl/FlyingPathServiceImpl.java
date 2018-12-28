package hust.plane.service.impl;

import hust.plane.mapper.mapper.FlyingPathMapper;
import hust.plane.mapper.pojo.FlyingPath;
import hust.plane.service.interFace.FlyingPathService;
import hust.plane.utils.KMLUtil;
import hust.plane.utils.page.TailPage;
import hust.plane.utils.pojo.PlanePathVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FlyingPathServiceImpl implements FlyingPathService {

    @Autowired
    private FlyingPathMapper flyingPathMapper;

    //导出飞向路径
    @Override
    public void importFlyingPath(FlyingPath flyingPath, String filePath) {
        FlyingPath planePathList = flyingPathMapper.selectByFlyingPathVO(flyingPath);
        List<PlanePathVo> plist = KMLUtil.textToList(planePathList.getPathdata(), planePathList.getHeightdata());
        KMLUtil.importKML(filePath, plist);
    }

    // 插入一条飞行路径
    @Override
    public boolean insertFlyingPath(FlyingPath flyingPath) {

        flyingPath.setPathdata("LINESTRING" + flyingPath.getPathdata());
        Date date = new Date();
        flyingPath.setCreatetime(date);
        flyingPath.setUpdatetime(date);

        // 然后在下面进行插入数据
        if (flyingPathMapper.insertFlyingPath(flyingPath) == 1)
            return true;
        else
            return false;
    }

    @Override
    public FlyingPath selectByFlyingPathId(Integer id) {

        FlyingPath path = flyingPathMapper.selectByFlyingPathId(id);
        return path;
    }

    @Override
    public FlyingPath selectByFlyingPathIdWithoutData(Integer id) {
        FlyingPath path = flyingPathMapper.selectByFlyingPathIdWithoutData(id);
        return path;
    }

    @Override
    public String getNameById(Integer id) {
        return flyingPathMapper.getNameById(id);
    }

    @Override
    public List<String> fuzzySearchByName(String queryString) {

        List<String> flyingPathNameList = null;
        flyingPathNameList = flyingPathMapper.fuzzySearchByName(queryString);

        return flyingPathNameList;

    }

    @Override
    public FlyingPath getFlyingPathByName(String name) {

        FlyingPath flyingPath = null;
        flyingPath = flyingPathMapper.getFlyingPathByName(name);
        return flyingPath;
    }

    @Override
    public TailPage<FlyingPath> queryFlyingPathWithPage(FlyingPath flyingPath, TailPage<FlyingPath> page) {

        List<FlyingPath> flyingPaths = null;

        int itemsTotalCount = flyingPathMapper.flyingPathCount(flyingPath);
        // 避免 搜索结果的分页页码数大于数据条数
        //查看当前条目的分页的页数
        int totalPageNum = itemsTotalCount % page.getPageSize() == 0 ? itemsTotalCount / page.getPageSize() : itemsTotalCount / page.getPageSize() + 1;

        if (page.getPageNum() == 0 || page.getPageNum() > totalPageNum) {
            page.setPageNum(1);
        }
        if (itemsTotalCount > 0) {
            flyingPaths = flyingPathMapper.queryFlyingPathPage(flyingPath, page);
        }

        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(flyingPaths);
        return page;
    }

    @Override
    public List<FlyingPath> findAllFlyingPath() {

        List<FlyingPath> planePaths = flyingPathMapper.findAllFlyingPath();
        return planePaths;
    }

    @Override
    public boolean deleteFlyingPath(FlyingPath flyingPath) {

        flyingPathMapper.deleteFlyingPath(flyingPath);
        return true;
    }

}
