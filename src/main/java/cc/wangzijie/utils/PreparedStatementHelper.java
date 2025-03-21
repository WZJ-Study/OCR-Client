package cc.wangzijie.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class PreparedStatementHelper {



    public static void setBooleanOrNull(PreparedStatement ps, int index, Boolean value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.BOOLEAN);
        } else {
            ps.setBoolean(index, value);
        }
    }


    public static void setIntOrNull(PreparedStatement ps, int index, Integer value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.INTEGER);
        } else {
            ps.setInt(index, value);
        }
    }

    public static void setLongOrNull(PreparedStatement ps, int index, Long value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.BIGINT);
        } else {
            ps.setLong(index, value);
        }
    }

    public static void setStringOrNull(PreparedStatement ps, int index, String value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.VARCHAR);
        } else {
            ps.setString(index, value);
        }
    }

    public static void setTimestampOrNull(PreparedStatement ps, int index, java.sql.Timestamp value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.TIMESTAMP);
        } else {
            ps.setTimestamp(index, value);
        }
    }

    public static void setDateOrNull(PreparedStatement ps, int index, java.sql.Date value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.DATE);
        } else {
            ps.setDate(index, value);
        }
    }

    public static void setTimeOrNull(PreparedStatement ps, int index, java.sql.Time value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.TIME);
        } else {
            ps.setTime(index, value);
        }
    }

    public static void setObjectOrNull(PreparedStatement ps, int index, Object value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.OTHER);
        } else {
            ps.setObject(index, value);
        }
    }

    public static void setBytesOrNull(PreparedStatement ps, int index, byte[] value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.VARBINARY);
        } else {
            ps.setBytes(index, value);
        }
    }

    public static void setClobOrNull(PreparedStatement ps, int index, String value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.CLOB);
        } else {
            ps.setClob(index, new java.io.StringReader(value));
        }
    }

    public static void setBlobOrNull(PreparedStatement ps, int index, byte[] value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.BLOB);
        } else {
            ps.setBlob(index, new java.io.ByteArrayInputStream(value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, Object[] value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("VARCHAR", value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, Integer[] value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("INTEGER", value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, Long[] value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("BIGINT", value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, Double[] value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("DOUBLE", value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, Float[] value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("FLOAT", value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, Boolean[] value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("BOOLEAN", value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.sql.Date[] value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("DATE", value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.sql.Time[] value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("TIME", value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.sql.Timestamp[] value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("TIMESTAMP", value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.util.Date[] value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            java.sql.Timestamp[] timestamps = new java.sql.Timestamp[value.length];
            for (int i = 0; i < value.length; i++) {
                timestamps[i] = new java.sql.Timestamp(value[i].getTime());
                try {
                    timestamps[i] = new java.sql.Timestamp(value[i].getTime());
                } catch (Exception e) {
                    timestamps[i] = null;
                }
            }
            ps.setArray(index, ps.getConnection().createArrayOf("TIMESTAMP", timestamps));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.util.UUID[] value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("UUID", value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.util.UUID value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("UUID", new java.util.UUID[] { value }));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.util.UUID[] value, String type) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf(type, value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.util.UUID value, String type) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf(type, new java.util.UUID[] { value }));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.util.Date value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("TIMESTAMP", new java.sql.Timestamp[] { new java.sql.Timestamp(value.getTime()) }));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.util.Date value, String type) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf(type, new java.sql.Timestamp[] { new java.sql.Timestamp(value.getTime()) }));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.sql.Date value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("DATE", new java.sql.Date[] { value }));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.sql.Date value, String type) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf(type, new java.sql.Date[] { value }));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.sql.Time value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("TIME", new java.sql.Time[] { value }));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.sql.Time value, String type) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf(type, new java.sql.Time[] { value }));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.sql.Timestamp value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf("TIMESTAMP", new java.sql.Timestamp[] { value }));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.sql.Timestamp value, String type) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf(type, new java.sql.Timestamp[] { value }));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.sql.Date[] value, String type) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf(type, value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.sql.Time[] value, String type) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf(type, value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.sql.Timestamp[] value, String type) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf(type, value));
        }
    }

    public static void setArrayOrNull(PreparedStatement ps, int index, java.util.Date[] value, String type) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.ARRAY);
        } else {
            ps.setArray(index, ps.getConnection().createArrayOf(type, new java.sql.Timestamp[] { new java.sql.Timestamp(value[0].getTime()) }));
        }
    }


}
