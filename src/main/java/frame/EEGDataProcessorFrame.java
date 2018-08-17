package frame;

import application.ApplicationData;
import application.Parameters;
import feature.AnalyzeSamplesFile;
import feature.Feature;
import feature.FeatureStatus;
import feature.PrepareChannelInOneRowFile;
import feature.PrepareSingleLabelFiles;
import feature.PrepareSingleTestFiles;
import feature.PrepareTestInOneRowFile;

import java.io.File;
import javax.swing.JFileChooser;

import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

public class EEGDataProcessorFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = -5065287789826423012L;

	public EEGDataProcessorFrame() {
		initComponents();
	}

	private ApplicationData applicationData;

	private final static int ROW_ID_STIMULUS = 0;
	private final static int ROW_ID_INTERVAL_LEFT = 1;
	private final static int ROW_ID_INTERVAL_RIGHT = 2;
	private final static int ROW_ID_DURATION_MIN = 3;
	private final static int ROW_ID_TRIGGERTIME_AVERAGE = 4;
	private final static int ROW_ID_TIMESTEP = 5;

	private void showParameters() {
		TableModel model = jtParameters.getModel();
		model.setValueAt(Long.toString(applicationData.getStimulusTime()), ROW_ID_STIMULUS, 1);
		model.setValueAt(Long.toString(applicationData.getIntervalLeft()), ROW_ID_INTERVAL_LEFT, 1);
		model.setValueAt(Long.toString(applicationData.getIntervalRight()), ROW_ID_INTERVAL_RIGHT, 1);
		model.setValueAt(Long.toString(applicationData.getDurationMin()), ROW_ID_DURATION_MIN, 1);
		model.setValueAt(Long.toString(applicationData.getTriggerTimeAverage()), ROW_ID_TRIGGERTIME_AVERAGE, 1);
		model.setValueAt(Long.toString(applicationData.getIndicationsFileTimeStep()), ROW_ID_TIMESTEP, 1);
	}

	public EEGDataProcessorFrame(ApplicationData applicationData) {
		this();
		this.applicationData = applicationData;
		showParameters();

		TableModel model = jtParameters.getModel();
		model.addTableModelListener((TableModelEvent e) -> {
			if (e.getType() == TableModelEvent.UPDATE) {
				switch (e.getFirstRow()) {
				case ROW_ID_STIMULUS:
					applicationData.setStimulusTime(Long.parseLong((String) model.getValueAt(ROW_ID_STIMULUS, 1)));
					break;
				case ROW_ID_INTERVAL_LEFT:
					applicationData.setIntervalLeft(Long.parseLong((String) model.getValueAt(ROW_ID_INTERVAL_LEFT, 1)));
					break;
				case ROW_ID_INTERVAL_RIGHT:
					applicationData
							.setIntervalRight(Long.parseLong((String) model.getValueAt(ROW_ID_INTERVAL_RIGHT, 1)));
					break;
				case ROW_ID_DURATION_MIN:
					applicationData.setDurationMin(Long.parseLong((String) model.getValueAt(ROW_ID_DURATION_MIN, 1)));
					break;
				case ROW_ID_TRIGGERTIME_AVERAGE:
					applicationData.setTriggerTimeAverage(
							Long.parseLong((String) model.getValueAt(ROW_ID_TRIGGERTIME_AVERAGE, 1)));
					break;
				case ROW_ID_TIMESTEP:
					applicationData
							.setIndicationsFileTimeStep(Long.parseLong((String) model.getValueAt(ROW_ID_TIMESTEP, 1)));
				}
			}
		});
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	//<editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jfcParametersFile = new javax.swing.JFileChooser(new File("").getAbsoluteFile());
		jScrollPane1 = new javax.swing.JScrollPane();
		jtaOutput = new javax.swing.JTextArea();
		jScrollPane2 = new javax.swing.JScrollPane();
		jtParameters = new javax.swing.JTable();
		jmbMainMenu = new javax.swing.JMenuBar();
		jmFile = new javax.swing.JMenu();
		jmiSelectFiles = new javax.swing.JMenuItem();
		jSeparator1 = new javax.swing.JPopupMenu.Separator();
		jmiExit = new javax.swing.JMenuItem();
		jmParameters = new javax.swing.JMenu();
		jmiParametersOpen = new javax.swing.JMenuItem();
		jmiParametersSave = new javax.swing.JMenuItem();
		jmiParametersSaveAs = new javax.swing.JMenuItem();
		jSeparator2 = new javax.swing.JPopupMenu.Separator();
		jmiAnalyzeSamplesFile = new javax.swing.JMenuItem();
		jmTask = new javax.swing.JMenu();
		jmiPrepareSingleTestFiles = new javax.swing.JMenuItem();
		jmiPrepareSingleLabelFiles = new javax.swing.JMenuItem();
		jmiPrepareTestInOneRowFile = new javax.swing.JMenuItem();
		jmiPrepareChannelInOneRowFile = new javax.swing.JMenuItem();
		jmHelp = new javax.swing.JMenu();
		jmiHelp = new javax.swing.JMenuItem();
		jmiAbout = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("EEGDataProcessor");
		setLocationByPlatform(true);

		jtaOutput.setEditable(false);
		jtaOutput.setColumns(20);
		jtaOutput.setRows(5);
		jScrollPane1.setViewportView(jtaOutput);

		jtParameters.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { "stimulusTime", "" }, { "interval_left", null }, { "interval_right", null },
						{ "duration_min", null }, { "triggerTime_average", null }, { "timeStep", null } },
				new String[] { "Параметр", "Значение" }) {
			private static final long serialVersionUID = -7692149791349207355L;
			Class<?>[] types = new Class[] { java.lang.String.class, java.lang.String.class };
			boolean[] canEdit = new boolean[] { false, true };

			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane2.setViewportView(jtParameters);

		jmFile.setText("Файл");

		jmiSelectFiles.setText("Выбрать файлы...");
		jmiSelectFiles.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiSelectFilesActionPerformed(evt);
			}
		});
		jmFile.add(jmiSelectFiles);
		jmFile.add(jSeparator1);

		jmiExit.setText("Выход");
		jmiExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiExitActionPerformed(evt);
			}
		});
		jmFile.add(jmiExit);

		jmbMainMenu.add(jmFile);

		jmParameters.setText("Параметры");

		jmiParametersOpen.setText("Открыть...");
		jmiParametersOpen.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiParametersOpenActionPerformed(evt);
			}
		});
		jmParameters.add(jmiParametersOpen);

		jmiParametersSave.setText("Сохранить");
		jmiParametersSave.setEnabled(false);
		jmiParametersSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiParametersSaveActionPerformed(evt);
			}
		});
		jmParameters.add(jmiParametersSave);

		jmiParametersSaveAs.setText("Сохранить как...");
		jmiParametersSaveAs.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiParametersSaveAsActionPerformed(evt);
			}
		});
		jmParameters.add(jmiParametersSaveAs);
		jmParameters.add(jSeparator2);

		jmiAnalyzeSamplesFile.setText("Анализ файла меток");
		jmiAnalyzeSamplesFile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiAnalyzeSamplesFileActionPerformed(evt);
			}
		});
		jmParameters.add(jmiAnalyzeSamplesFile);

		jmbMainMenu.add(jmParameters);

		jmTask.setText("Задача");

		jmiPrepareSingleTestFiles.setText("Подготовить файлы \"Проба\"");
		jmiPrepareSingleTestFiles.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiPrepareSingleTestFilesActionPerformed(evt);
			}
		});
		jmTask.add(jmiPrepareSingleTestFiles);

		jmiPrepareSingleLabelFiles.setText("Подготовить файлы \"Метка\"");
		jmiPrepareSingleLabelFiles.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiPrepareSingleLabelFilesActionPerformed(evt);
			}
		});
		jmTask.add(jmiPrepareSingleLabelFiles);

		jmiPrepareTestInOneRowFile.setText("Разместить пробы в строку");
		jmiPrepareTestInOneRowFile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiPrepareTestInOneRowFileActionPerformed(evt);
			}
		});
		jmTask.add(jmiPrepareTestInOneRowFile);

		jmiPrepareChannelInOneRowFile.setText("Разместить каналы в строку");
		jmiPrepareChannelInOneRowFile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiPrepareChannelInOneRowFileActionPerformed(evt);
			}
		});
		jmTask.add(jmiPrepareChannelInOneRowFile);

		jmbMainMenu.add(jmTask);

		jmHelp.setText("?");

		jmiHelp.setText("Справка");
		jmiHelp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jmiHelpActionPerformed(evt);
			}
		});
		jmHelp.add(jmiHelp);

		jmiAbout.setText("О программе...");
		jmHelp.add(jmiAbout);

		jmbMainMenu.add(jmHelp);

		setJMenuBar(jmbMainMenu);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1)
						.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
						.addContainerGap()));

		pack();
	}//</editor-fold>//GEN-END:initComponents

	private void jmiPrepareSingleLabelFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPrepareSingleLabelFilesActionPerformed
		java.awt.EventQueue.invokeLater(() -> {
			Feature feature = new PrepareSingleLabelFiles(applicationData);
			new TaskProgressFrame(feature).setVisible(true);
		});
	}//GEN-LAST:event_jmiPrepareSingleLabelFilesActionPerformed

	private void jmiParametersOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiParametersOpenActionPerformed
		java.awt.EventQueue.invokeLater(() -> {
			int result = jfcParametersFile.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = jfcParametersFile.getSelectedFile();
				Parameters parameters = new Parameters();
				parameters.load(file);
				applicationData.setParameters(parameters);
				showParameters();

				jmiParametersSave.setEnabled(true);
			}
		});
	}//GEN-LAST:event_jmiParametersOpenActionPerformed

	private void jmiParametersSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiParametersSaveActionPerformed
		java.awt.EventQueue.invokeLater(() -> {
			File file = jfcParametersFile.getSelectedFile();
			Parameters parameters = applicationData.getParameters();
			parameters.save(file);
		});
	}//GEN-LAST:event_jmiParametersSaveActionPerformed

	private void jmiParametersSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiParametersSaveAsActionPerformed
		java.awt.EventQueue.invokeLater(() -> {
			int result = jfcParametersFile.showSaveDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				jmiParametersSaveActionPerformed(evt);
				jmiParametersSave.setEnabled(true);
			}
		});
	}//GEN-LAST:event_jmiParametersSaveAsActionPerformed

	private void jmiAnalyzeSamplesFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAnalyzeSamplesFileActionPerformed
		java.awt.EventQueue.invokeLater(() -> {
			Feature feature = new AnalyzeSamplesFile(applicationData);
			feature.addFeatureStatusListener((FeatureStatus event) -> {
				if (event.getType() == FeatureStatus.COMPLETED) {
					showParameters();
				}
			});
			new TaskProgressFrame(feature).setVisible(true);
		});
	}//GEN-LAST:event_jmiAnalyzeSamplesFileActionPerformed

	private void jmiExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExitActionPerformed
		this.dispose();
	}//GEN-LAST:event_jmiExitActionPerformed

	private void jmiSelectFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSelectFilesActionPerformed
		java.awt.EventQueue.invokeLater(() -> {
			new SelectFilesFrame(applicationData).setVisible(true);
		});
	}//GEN-LAST:event_jmiSelectFilesActionPerformed

	private void jmiPrepareSingleTestFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPrepareSeparateTestFilesActionPerformed
		java.awt.EventQueue.invokeLater(() -> {
			Feature feature = new PrepareSingleTestFiles(applicationData);
			new TaskProgressFrame(feature).setVisible(true);
		});
	}//GEN-LAST:event_jmiPrepareSeparateTestFilesActionPerformed

	private void jmiPrepareTestInOneRowFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPrepareTestInOneRowFileActionPerformed
		java.awt.EventQueue.invokeLater(() -> {
			Feature feature = new PrepareTestInOneRowFile(applicationData);
			new TaskProgressFrame(feature).setVisible(true);
		});
	}//GEN-LAST:event_jmiPrepareTestInOneRowFileActionPerformed

	private void jmiPrepareChannelInOneRowFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPrepareChannelInOneRowFileActionPerformed
		java.awt.EventQueue.invokeLater(() -> {
			Feature feature = new PrepareChannelInOneRowFile(applicationData);
			new TaskProgressFrame(feature).setVisible(true);
		});
	}//GEN-LAST:event_jmiPrepareChannelInOneRowFileActionPerformed

	private void jmiHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiHelpActionPerformed
		java.awt.EventQueue.invokeLater(() -> {
			new HelpFrame().setVisible(true);
		});
	}//GEN-LAST:event_jmiHelpActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JPopupMenu.Separator jSeparator1;
	private javax.swing.JPopupMenu.Separator jSeparator2;
	private javax.swing.JFileChooser jfcParametersFile;
	private javax.swing.JMenu jmFile;
	private javax.swing.JMenu jmHelp;
	private javax.swing.JMenu jmParameters;
	private javax.swing.JMenu jmTask;
	private javax.swing.JMenuBar jmbMainMenu;
	private javax.swing.JMenuItem jmiAbout;
	private javax.swing.JMenuItem jmiAnalyzeSamplesFile;
	private javax.swing.JMenuItem jmiExit;
	private javax.swing.JMenuItem jmiHelp;
	private javax.swing.JMenuItem jmiParametersOpen;
	private javax.swing.JMenuItem jmiParametersSave;
	private javax.swing.JMenuItem jmiParametersSaveAs;
	private javax.swing.JMenuItem jmiPrepareChannelInOneRowFile;
	private javax.swing.JMenuItem jmiPrepareSingleLabelFiles;
	private javax.swing.JMenuItem jmiPrepareSingleTestFiles;
	private javax.swing.JMenuItem jmiPrepareTestInOneRowFile;
	private javax.swing.JMenuItem jmiSelectFiles;
	private javax.swing.JTable jtParameters;
	private javax.swing.JTextArea jtaOutput;
	// End of variables declaration//GEN-END:variables
}
