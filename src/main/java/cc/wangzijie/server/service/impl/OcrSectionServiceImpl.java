package cc.wangzijie.server.service.impl;

import cc.wangzijie.server.entity.OcrSection;
import cc.wangzijie.server.service.IOcrSectionService;
import cc.wangzijie.utils.PreparedStatementHelper;
import cc.wangzijie.utils.SnowflakeIdWorker;
import javafx.scene.shape.Rectangle;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
     */
    @Override
    public void save(OcrSection entity) {
        if (entity == null) {
            return;
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
            ps.executeUpdate();
        } catch (Exception e) {
            log.error("==== save ==== 保存失败！", e);
        }
    }

    /**
     * 根据ID更新
     *
     * @param id   区域ID
     * @param name 区域名称
     * @param type 区域类型
     */
    @Override
    public void updateNameTypeById(Long id, String name, String type) {
        if (id == null) {
            return;
        } else if (StringUtils.isEmpty(name) && StringUtils.isEmpty(type)) {
            return;
        }
        StringBuilder sql = new StringBuilder(" update ocr_section set ");
        boolean commaFlag = false;
        if (StringUtils.isNotEmpty(name)) {
            sql.append(" name = '").append(name).append("'");
            commaFlag = true;
        }
        if (StringUtils.isNotEmpty(type)) {
            if (commaFlag) {
                sql.append(",");
            }
            sql.append(" type = '").append(type).append("'");
        }
        sql.append(" where id = '").append(id).append("' ");
        String sqlStr = sql.toString();
        log.info("==== updateNameTypeById ==== 准备SQL：{}", sqlStr);

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sqlStr);
            ps.executeUpdate();
        } catch (Exception e) {
            log.error("==== updateNameTypeById ==== 保存失败！", e);
        }
    }



    @Override
    public OcrSection createNewSection(Rectangle rect) {
        OcrSection entity = new OcrSection();
        entity.setId(SnowflakeIdWorker.generateId());
        entity.setName("字段#" + System.currentTimeMillis());
        entity.fillByRect(rect);
        entity.displayPosition();
        entity.setType("文本");
        service.save(entity);
        return entity;
    }
}
