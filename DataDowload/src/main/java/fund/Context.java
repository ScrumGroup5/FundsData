package fund;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fund.model.DataUtil;
import fund.model.Fund;

public class Context {
	private static final Logger logger = LoggerFactory.getLogger(Context.class);

	private volatile static Context uniqueInstance;

	private Context() {
	}

	private ObservableList<Fund> funds = null;

	public static Context getInstance() {
		if (uniqueInstance == null) {
			synchronized (Context.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Context();
				}
			}
		}
		return uniqueInstance;
	}

	public ObservableList<Fund> getFunds() {
		return funds;
	}

	public void loadFunds() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(
					DataUtil.getFundCodeFilePath())));
			String tmp = null;
			funds = FXCollections.observableArrayList();
			while ((tmp = reader.readLine()) != null) {
				String[] attrs = tmp.split(" ");
				funds.add(new Fund(attrs[0]));
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
