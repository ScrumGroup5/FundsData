/**
 * 
 */
package fund.controller;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import fund.Context;
import fund.model.Fund;
import fund.model.Record;

/**
 * @author Saint Scott
 *
 */
public class InfoController extends AbstractFXController {

	@FXML
	private Parent rootLayout;

	@FXML
	private TableView<Fund> fundsTable;

	@FXML
	private TableColumn<Fund, String> codeColumn;
	@FXML
	private TableColumn<Fund, String> nameColumn;

	@FXML
	private Button checkHistory;
	@FXML
	private Label fullNameLabel;
	@FXML
	private Label codeLabel;
	@FXML
	private Label publishDateLabel;
	@FXML
	private Label propertySizeLabel;
	@FXML
	private Label organizationLabel;
	@FXML
	private Label managerLabel;
	@FXML
	private Label manageFeeRateLabel;
	@FXML
	private Label workStandardLabel;
	@FXML
	private Label briefNameLabel;
	@FXML
	private Label typeLabel;
	@FXML
	private Label establishLabel;
	@FXML
	private Label portionSizeLabel;
	@FXML
	private Label trusteeLabel;
	@FXML
	private Label shareProfitLabel;
	@FXML
	private Label trusteeFeeRateLabel;
	@FXML
	private Label trackSubjectLabel;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fund.controller.AbstractFXController#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@FXML
	private void initialize() {
		codeColumn.setCellValueFactory(new PropertyValueFactory<Fund, String>(
				"code"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<Fund, String>(
				"name"));
		checkHistory.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Fund fund = fundsTable.getSelectionModel().getSelectedItem();
				createDialog(fund);
			}
		});
		fundsTable.setItems(Context.getInstance().getFunds());
		fundsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		fundsTable.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Fund>() {

					@Override
					public void changed(
							ObservableValue<? extends Fund> observable,
							Fund oldValue, Fund newValue) {
						// TODO Auto-generated method stub
						showProfile(newValue);
					}
				});
		fundsTable.getSelectionModel().select(0);
	}

	private void showProfile(Fund fund) {
		if (fund != null) {
			fullNameLabel.setText(fund.getName());
			codeLabel.setText(fund.getCode());
			publishDateLabel.setText(fund.getPublishDate());
			propertySizeLabel.setText(fund.getPropertySize());
			organizationLabel.setText(fund.getOrganization());
			managerLabel.setText(fund.getManager());
			manageFeeRateLabel.setText(fund.getManageFeeRate());
			workStandardLabel.setText(fund.getWorkStandard());
			briefNameLabel.setText(fund.getBriefName());
			typeLabel.setText(fund.getType());
			establishLabel.setText(fund.getEstablishDateAndSize());
			portionSizeLabel.setText(fund.getPortionSize());
			trusteeLabel.setText(fund.getTrustee());
			shareProfitLabel.setText(fund.getShareProfit());
			trusteeFeeRateLabel.setText(fund.getTrusteeFeeRate());
			trackSubjectLabel.setText(fund.getTrackSubject());
		} else {
			fullNameLabel.setText(null);
			codeLabel.setText(null);
			publishDateLabel.setText(null);
			propertySizeLabel.setText(null);
			organizationLabel.setText(null);
			managerLabel.setText(null);
			manageFeeRateLabel.setText(null);
			workStandardLabel.setText(null);
			briefNameLabel.setText(null);
			typeLabel.setText(null);
			establishLabel.setText(null);
			portionSizeLabel.setText(null);
			trusteeLabel.setText(null);
			shareProfitLabel.setText(null);
			trusteeFeeRateLabel.setText(null);
			trackSubjectLabel.setText(null);
		}
	}

	private void createDialog(Fund fund) {
		Stage dialogStage = new Stage();
		dialogStage.setTitle("历史净值");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(rootLayout.getScene().getWindow());

		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(createCharts(fund));
		Scene scene = new Scene(stackPane);
		dialogStage.setScene(scene);
		dialogStage.setWidth(960);
		dialogStage.showAndWait();
	}

	private LineChart<String, Number> createCharts(Fund fund) {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		yAxis.setAutoRanging(true);
		LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
		List<Record> list = fund.getRecordList();

		list.sort(new Comparator<Record>() {

			@Override
			public int compare(Record o1, Record o2) {
				// TODO Auto-generated method stub
				return o1.getDate().compareTo(o2.getDate());
			}
		});

		XYChart.Series<String, Number> series = new XYChart.Series<>();

		if (fund.getDatatype() == 6) {
			for (Iterator<Record> iterator = list.iterator(); iterator
					.hasNext();) {
				Record record = iterator.next();

				String rate = record.getTenThousandIncome();
				series.getData().add(
						new Data<String, Number>(record.getDate(), new Double(
								rate.substring(0, rate.indexOf('%')))));
			}
		} else {
			for (Iterator<Record> iterator = list.iterator(); iterator
					.hasNext();) {
				Record record = iterator.next();

				String rate = record.getUnitNet();
				series.getData().add(
						new Data<String, Number>(record.getDate(), new Double(
								rate)));
			}
		}
		chart.setCreateSymbols(false);
		chart.getStylesheets().add(
				getClass().getResource("/chart.css").toExternalForm());
		chart.getData().add(series);

		return chart;
	}
}
