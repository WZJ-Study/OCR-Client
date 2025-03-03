package cc.wangzijie.ui.utils;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class DrawUtils {
    //窗体拉伸属性
    private boolean isRight;// 是否处于右边界调整窗口状态
    private boolean isLeft;// 是否处于左边界调整窗口状态
    private boolean isTop;// 是否处于上边界调整窗口状态
    private boolean isBottom;// 是否处于下边界调整窗口状态
    private boolean isTopLeft;// 是否处于左上角调整窗口状态
    private boolean isTopRight;// 是否处于右上角调整窗口状态
    private boolean isBottomRight;// 是否处于右下角调整窗口状态
    private boolean isBottomLeft;// 是否处于左下角调整窗口状态

    private Cursor cursorType;// 鼠标光标类型

    private double nextWidth;
    private double nextHeight;

    private double xOffSet;
    private double yOffSet;

    private long lastUpdateTime;


    private final static int RESIZE_WIDTH = 5;// 判定是否为调整窗口状态的范围与边界距离
    private final double MIN_WIDTH;// 窗口最小宽度
    private final double MIN_HEIGHT;// 窗口最小高度

    public DrawUtils(double minWidth, double minHeight) {
        MIN_WIDTH = minWidth;
        MIN_HEIGHT = minHeight;
    }

    public DrawUtils() {
        MIN_WIDTH = 0;
        MIN_HEIGHT = 0;
    }

    /**
     * 添加拉伸窗口功能
     *
     * @param stage
     * @param root
     */
    public void addStretch(Stage stage, Node root) {


        // 添加鼠标移动事件,检测鼠标位置，修改鼠标样式
        root.setOnMouseMoved((MouseEvent event) -> {
            event.consume();
            // 重置所有调整窗口状态
            isRight = isBottomRight = isBottom = isLeft = isTop = isTopRight = isTopLeft = isBottomLeft = false;

            double sceneX = event.getSceneX();//获取场景X
            double sceneY = event.getSceneY();//获取场景Y

            double width = stage.getWidth();
            double height = stage.getHeight();

            // 检查顶部区域
            if (sceneY < RESIZE_WIDTH) {
                if (sceneX < RESIZE_WIDTH) {// 左上角
                    isTopLeft = true;
                    cursorType = Cursor.NW_RESIZE;
                } else if (sceneX > width - RESIZE_WIDTH) {// 右上角
                    isTopRight = true;
                    cursorType = Cursor.NE_RESIZE;
                } else {//顶部
                    isTop = true;
                    cursorType = Cursor.N_RESIZE;
                }
            }

            // 检查底部区域
            else if (sceneY > height - RESIZE_WIDTH) {
                if (sceneX < RESIZE_WIDTH) {// 左下角
                    isBottomLeft = true;
                    cursorType = Cursor.SW_RESIZE;
                } else if (sceneX > width - RESIZE_WIDTH) {// 右下角
                    isBottomRight = true;
                    cursorType = Cursor.SE_RESIZE;
                } else {
                    isBottom = true;
                    cursorType = Cursor.S_RESIZE;
                }
            }

            // 检查左右边界
            if (sceneX < RESIZE_WIDTH && sceneX > -RESIZE_WIDTH) {
                isLeft = true;
                cursorType = Cursor.W_RESIZE;
            } else if (sceneX > width - RESIZE_WIDTH && sceneX < width + RESIZE_WIDTH) {
                isRight = true;
                cursorType = Cursor.E_RESIZE;
            }

            // 默认情况
            else if (sceneX > RESIZE_WIDTH && sceneX < width - RESIZE_WIDTH && sceneY > RESIZE_WIDTH && sceneY < height - RESIZE_WIDTH) {
                cursorType = Cursor.DEFAULT;
            }

            // 最后改变鼠标光标
            root.setCursor(cursorType);
        });

        // 添加鼠标按下事件
        root.setOnMousePressed((MouseEvent event) -> {
            xOffSet = event.getX();
            yOffSet = event.getY();
            nextWidth = stage.getWidth();
            nextHeight = stage.getHeight();
        });

        // 添加鼠标拖拽事件,动态增大窗口
        root.setOnMouseDragged((MouseEvent event) -> {

            if (isTop || isTopLeft || isTopRight || isBottom || isBottomLeft || isBottomRight || isLeft || isRight) {

                double mouseX = event.getX();
                double mouseY = event.getY();
                // 保存窗口改变后的x、y坐标和宽度、高度，用于预判是否会小于最小宽度、最小高度
                double nextX = stage.getX();
                double nextY = stage.getY();

                if (System.currentTimeMillis() - lastUpdateTime > 10) {

                    if (isTop || isTopLeft || isTopRight) {// 所有上边调整窗口状态
                        nextY = nextY + (mouseY - yOffSet);
                        nextHeight = nextHeight - (mouseY - yOffSet);

                    }
                    if (isLeft || isTopLeft || isBottomLeft) {// 所有左边调整窗口状态
                        nextX = nextX + (mouseX - xOffSet);
                        nextWidth = nextWidth - (mouseX - xOffSet);

                    }

                    if (isRight || isTopRight || isBottomRight) {// 所有右边调整窗口状态
                        nextWidth = mouseX;

                    }
                    if (isBottom || isBottomLeft || isBottomRight) {// 所有下边调整窗口状态
                        nextHeight = mouseY;

                    }

                    if (MIN_WIDTH != 0 && MIN_HEIGHT != 0) {
                        if (nextWidth <= MIN_WIDTH) {// 如果窗口改变后的宽度小于最小宽度，则宽度调整到最小宽度
                            nextWidth = MIN_WIDTH;
                        }
                        if (nextHeight <= MIN_HEIGHT) {// 如果窗口改变后的高度小于最小高度，则高度调整到最小高度
                            nextHeight = MIN_HEIGHT;
                        }
                    }


                    // 使用缓冲区或延迟更新逻辑，避免频繁更新
                    stage.setX(nextX);
                    stage.setY(nextY);

                    stage.setWidth(nextWidth);
                    stage.setHeight(nextHeight);

//                    stage.sizeToScene();
                    lastUpdateTime = System.currentTimeMillis();
                }

            }

        });
    }

    /**
     * 添加移动窗口功能
     * 鼠标拖动指定组件才可以移动窗口
     *
     * @param stage
     * @param node
     */
    public void addMoveWindow(Stage stage, Node node) {
        node.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {
            stage.setX(event.getScreenX() - xOffSet);
            stage.setY(event.getScreenY() - yOffSet);
        });
    }

    /**
     * 添加移动窗口功能
     * 鼠标拖动窗口所有地方都可以移动
     *
     * @param stage
     */
    public void addMoveWindow(Stage stage) {
        stage.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {
            stage.setX(event.getScreenX() - xOffSet);
            stage.setY(event.getScreenY() - yOffSet);
        });
    }
}
