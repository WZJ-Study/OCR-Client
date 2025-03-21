package cc.wangzijie.server.service.impl;

import cc.wangzijie.server.entity.OcrSectionResult;
import cc.wangzijie.server.service.IOcrSectionResultService;
import cc.wangzijie.utils.PreparedStatementHelper;
import cc.wangzijie.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class OcrSectionResultServiceImpl implements IOcrSectionResultService {

    @Resource
    private DataSource dataSource;

    private static final String INSERT_SQL = "insert into ocr_section_result " +
            "( id, section_id, name, position, x, y, trans_x, trans_y, width, height, type, value, collect_time ) " +
            "values " +
            "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
    private static final int BATCH_SIZE = 100;

    @Override
    public List<OcrSectionResult> searchList(String collectTimeBegin, String collectTimeEnd, String name) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" SELECT * FROM ocr_section_result WHERE 1=1 ");
        if (StringUtils.isNotBlank(collectTimeBegin)) {
            sqlBuilder.append(" and collect_time >= '").append(collectTimeBegin).append("' ");
        }
        if (StringUtils.isNotBlank(collectTimeEnd)) {
            sqlBuilder.append(" and collect_time <= '").append(collectTimeEnd).append("' ");
        }
        if (StringUtils.isNotBlank(name)) {
            sqlBuilder.append(" and name like '%' || '").append(name).append("' || '%'");
        }
        String sql = sqlBuilder.toString();
        log.info("==== searchList ==== 准备SQL：{}", sql);
        List<OcrSectionResult> resultList = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OcrSectionResult result = new OcrSectionResult();
                result.setId(rs.getLong("id"));
                result.setSectionId(rs.getLong("section_id"));
                result.setName(rs.getString("name"));
                result.setPosition(rs.getString("position"));
                result.setX(rs.getInt("x"));
                result.setY(rs.getInt("y"));
                result.setTransX(rs.getInt("trans_x"));
                result.setTransY(rs.getInt("trans_y"));
                result.setWidth(rs.getInt("width"));
                result.setHeight(rs.getInt("height"));
                result.setType(rs.getString("type"));
                result.setValue(rs.getString("value"));
                result.setCollectTime(rs.getString("collect_time"));
                resultList.add(result);
            }
        } catch (Exception e) {
            log.error("==== searchList ==== 查询失败！", e);
        }
        return resultList;
    }


    /**
     * 创建新增
     *
     * @param entity 实体类对象
     */
    @Override
    public void save(OcrSectionResult entity) {
        if (entity == null) {
            return;
        }
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
            ps.setLong(1, entity.getId() == null ? SnowflakeIdWorker.generateId() : entity.getId());
            PreparedStatementHelper.setLongOrNull(ps, 2, entity.getSectionId());
            PreparedStatementHelper.setStringOrNull(ps, 3, entity.getName());
            PreparedStatementHelper.setStringOrNull(ps, 4, entity.getPosition());
            PreparedStatementHelper.setIntOrNull(ps, 5, entity.getX());
            PreparedStatementHelper.setIntOrNull(ps,6, entity.getY());
            PreparedStatementHelper.setIntOrNull(ps,7, entity.getTransX());
            PreparedStatementHelper.setIntOrNull(ps,8, entity.getTransY());
            PreparedStatementHelper.setIntOrNull(ps,9, entity.getWidth());
            PreparedStatementHelper.setIntOrNull(ps,10, entity.getHeight());
            PreparedStatementHelper.setStringOrNull(ps, 11, entity.getType());
            PreparedStatementHelper.setStringOrNull(ps, 12, entity.getValue());
            PreparedStatementHelper.setStringOrNull(ps, 13, entity.getCollectTime());
            ps.executeUpdate();
        } catch (Exception e) {
            log.error("==== save ==== 保存失败！", e);
        }
    }

    /**
     * 批量创建新增
     *
     * @param entityList 实体类对象列表
     */
    @Override
    public void saveBatch(Collection<OcrSectionResult> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return;
        }
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
            int index = 0;
            int total = entityList.size();
            for (OcrSectionResult entity : entityList) {
                index++;
                ps.setLong(1, entity.getId() == null ? SnowflakeIdWorker.generateId() : entity.getId());
                PreparedStatementHelper.setLongOrNull(ps, 2, entity.getSectionId());
                PreparedStatementHelper.setStringOrNull(ps, 3, entity.getName());
                PreparedStatementHelper.setStringOrNull(ps, 4, entity.getPosition());
                PreparedStatementHelper.setIntOrNull(ps, 5, entity.getX());
                PreparedStatementHelper.setIntOrNull(ps,6, entity.getY());
                PreparedStatementHelper.setIntOrNull(ps,7, entity.getTransX());
                PreparedStatementHelper.setIntOrNull(ps,8, entity.getTransY());
                PreparedStatementHelper.setIntOrNull(ps,9, entity.getWidth());
                PreparedStatementHelper.setIntOrNull(ps,10, entity.getHeight());
                PreparedStatementHelper.setStringOrNull(ps, 11, entity.getType());
                PreparedStatementHelper.setStringOrNull(ps, 12, entity.getValue());
                PreparedStatementHelper.setStringOrNull(ps, 13, entity.getCollectTime());
                ps.addBatch();
                if ((index % BATCH_SIZE == 0) || index == total) {
                    ps.executeBatch();
                    connection.commit();
                }
            }
        } catch (Exception e) {
            log.error("==== save ==== 保存失败！", e);
        }
    }

}
