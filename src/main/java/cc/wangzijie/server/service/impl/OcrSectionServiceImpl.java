package cc.wangzijie.server.service.impl;

import cc.wangzijie.server.entity.OcrSection;
import cc.wangzijie.server.service.IOcrSectionService;
import cc.wangzijie.utils.PreparedStatementHelper;
import cc.wangzijie.utils.SnowflakeIdWorker;
import javafx.scene.shape.Rectangle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;


@Slf4j
@Service
public class OcrSectionServiceImpl implements IOcrSectionService {

    @Resource
    private IOcrSectionService service;

    @Resource
    private DataSource dataSource;

    private static final String INSERT_SQL = "insert into ocr_section " +
                                             "( id, name, position, x, y, trans_x, trans_y, width, height, type ) " +
                                             "values " +
                                             "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";


    /**
     * 创建新增
     *
     * @param entity 实体类对象
     * @return 操作是否成功
     */
    @Override
    public boolean save(OcrSection entity) {
        if (entity == null) {
            return false;
        }
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
            ps.setLong(1, entity.getId() == null ? SnowflakeIdWorker.generateId() : entity.getId());
            PreparedStatementHelper.setStringOrNull(ps,2, entity.getName());
            PreparedStatementHelper.setStringOrNull(ps,3, entity.getPosition());
            PreparedStatementHelper.setIntOrNull(ps,4, entity.getX());
            PreparedStatementHelper.setIntOrNull(ps,5, entity.getY());
            PreparedStatementHelper.setIntOrNull(ps,6, entity.getTransX());
            PreparedStatementHelper.setIntOrNull(ps,7, entity.getTransY());
            PreparedStatementHelper.setIntOrNull(ps,8, entity.getWidth());
            PreparedStatementHelper.setIntOrNull(ps,9, entity.getHeight());
            PreparedStatementHelper.setStringOrNull(ps,10, entity.getType());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            log.error("==== save ==== 保存失败！", e);
        }
        return false;
    }

    @Override
    public OcrSection createNewSection(Rectangle rect) {
        OcrSection entity = new OcrSection();
        entity.setId(SnowflakeIdWorker.generateId());
        entity.setName("字段#" + System.currentTimeMillis());
        entity.fillByRect(rect);
        entity.displayPosition();
        entity.setType("文本");

        if (service.save(entity)) {
            log.info("==== createNewSection ==== 向数据库中保存成功！");
        };

        return entity;
    }
}
