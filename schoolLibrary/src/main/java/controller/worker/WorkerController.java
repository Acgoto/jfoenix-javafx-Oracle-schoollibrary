package controller.worker;

import controller.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Date;
import java.util.Map;

public class WorkerController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Accordion accordion;

    @FXML
    private Button selectReader;

    @FXML
    private Button borrowingCard;

    @FXML
    private Button selectBook;

    @FXML
    private Button bookIn;

    @FXML
    private Button altBookms;

    @FXML
    private Button reviewReturn;

    @FXML
    private Button breakContract;

    @FXML
    private Button workerMS;

    @FXML
    private Button altWpw;

    @FXML
    private AnchorPane anchorpaneR;

    @FXML
    private Label workName;

    @FXML
    private Label workId;

    @FXML
    private TitledPane titleFirstPane;

    @FXML
    private TitledPane titleSecondPane;

    @FXML
    private TitledPane titleThirdPane;

    @FXML
    private TitledPane titleFourthPane;

    @FXML
    private Button checkReview;

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public String getWorkerId() {
        return workId.getText();
    }

    public void initData(String wId, String wName) {
        workName.setText(wName);
        workId.setText(wId);
        //默认选择第一个标题，并且清空右边的内容，同时使得每个标题处于关闭状态
        //清空右边的面板
        anchorpaneR.getChildren().clear();
        //设置4个选项卡都为关闭状态
        titleFirstPane.setExpanded(false);
        titleSecondPane.setExpanded(false);
        titleThirdPane.setExpanded(false);
        titleFourthPane.setExpanded(false);
    }

    @FXML
    public void alBookmsClick() {
        try {
            anchorpaneR.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/worker/altBookMS.fxml"));
            anchorpaneR.getChildren().add((AnchorPane)loader.load());
            AltBookMSController abmc = loader.getController();
            abmc.clearData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void altWpwClick( ) {
        try {
            switchView("workerAltpw.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void bookInClik( ) {
        try {
            switchView("addBook.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void borrowingCardClick( ) {
        try {
            switchView("borrowingCard.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void breakContractClick( ) {
        try {
            anchorpaneR.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/worker/uncomply.fxml"));
            anchorpaneR.getChildren().add((AnchorPane)loader.load());
            UncomplyController uc = loader.getController();
            uc.initUncomplyData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void listDefault(MouseEvent event) {

    }

    @FXML
    public void returnToLoginBtnClick( ) {
        System.out.println("返回到登录界面~");
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.setTitle("登录主界面");
        stage.setScene(Main.lcScene);
        Main.lc.clearContent();
    }

    @FXML
    public void reviewReturnClick( ) {
        try {
            anchorpaneR.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/worker/reviewReturn.fxml"));
            anchorpaneR.getChildren().add((AnchorPane)loader.load());
            ReviewReturnController rrc = loader.getController();
            rrc.initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void selectBookClick( ) {
        try {
            anchorpaneR.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/worker/selectBook.fxml"));
            anchorpaneR.getChildren().add((AnchorPane)loader.load());
            SelectBookController sbc = loader.getController();
            sbc.initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void selectReaderClick( ) {
        try {
            switchView("selectReaderMS.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //点击审核借阅
    @FXML
    public void checkReviewClick() {
        try {
            anchorpaneR.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/worker/checkReviewBook.fxml"));
            anchorpaneR.getChildren().add((AnchorPane)loader.load());
            CheckReviewBookController cbc = loader.getController();
            cbc.initReserveCheckData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void titlePaneClick1(MouseEvent event) {
        accordion.setPrefHeight(360);
        System.out.println("点击读者管理");
    }

    @FXML
    public void titlePaneClick2(MouseEvent event) {
        accordion.setPrefHeight(420);

        System.out.println("点击图书管理");
    }

    @FXML
    public void titlePaneClick3(MouseEvent event) {
        accordion.setPrefHeight(420);
        System.out.println("点击借阅管理");
    }

    @FXML
    public void titlePaneClick4(MouseEvent event) {
        accordion.setPrefHeight(360);
        System.out.println("点击个人信息管理");
    }

    @FXML
    public void workerMSClick( ) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            anchorpaneR.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/worker/workerPersonalCenter.fxml"));
            anchorpaneR.getChildren().add((AnchorPane)loader.load());
            WorkerPeCeController wpc = (WorkerPeCeController) loader.getController();
            String workerId = Main.wc.getWorkerId();
            System.out.println(workerId);
            // 此处调用存储过程
            //1、获取数据库连接
            conn = JDBCUtils.getConnection();
            //2、定义调用存储过程的sql语句： {call <procedure-name>[(<arg1>,<arg2>, ...)]}
            String sql = "{call queryForWorkerMessage(?,?,?,?,?)}";
            //3、获取执行sql对象
            call = conn.prepareCall(sql);
            //4、对于IN参数，需要赋值
            call.setString(1, workerId);
            //5、对于OUT参数，需要申明为输出参数类型
            call.registerOutParameter(2, OracleTypes.VARCHAR);
            call.registerOutParameter(3, OracleTypes.VARCHAR);
            call.registerOutParameter(4, OracleTypes.VARCHAR);
            call.registerOutParameter(5, OracleTypes.VARCHAR);
            //6、执行调用
            call.execute();
            //7、取出所有结果
            String wName = call.getString(2);
            String wSex = call.getString(3);
            String wDate = call.getString(4);
            String job = call.getString(5);
            System.out.println(wName + " " + wSex + " " + wDate + " " + job);
            wpc.initData(workerId, wDate.split(" ")[0], wName, job , wSex);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(null, call, conn);
        }
    }

    //选择显示页面
    private void switchView(String fileName) throws Exception {
        //清空原有内容
        anchorpaneR.getChildren().clear();
        AnchorPane anchorPane = new FXMLLoader(getClass().getResource("/fxml/worker/" + fileName)).load();
        anchorpaneR.getChildren().add(anchorPane);
    }
}
