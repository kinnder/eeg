package frame;

import java.awt.EventQueue;

import feature.Feature;
import feature.FeatureStatus;
import feature.FeatureStatusListener;

public class TaskProgressFrame extends javax.swing.JDialog implements FeatureStatusListener {

	private static final long serialVersionUID = 1488232629320424390L;

	/**
	 * Creates new form TaskProgressFrame
	 */
	public TaskProgressFrame() {
		initComponents();
		setModal(true);
	}

	Thread featureThread;

	public TaskProgressFrame(Feature feature) {
		this();
		featureThread = new Thread(feature);
		feature.addFeatureStatusListener(this);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jpbProgress = new javax.swing.JProgressBar();
		jbCancel = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Выполнение");
		setLocationByPlatform(true);
		setType(java.awt.Window.Type.UTILITY);
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentShown(java.awt.event.ComponentEvent evt) {
				formComponentShown(evt);
			}
		});

		jbCancel.setText("Отмена");
		jbCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbCancelActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jpbProgress, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jbCancel)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap()
						.addComponent(jpbProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jbCancel).addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jbCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbCancelActionPerformed
		featureThread.interrupt();
		this.dispose();
	}// GEN-LAST:event_jbCancelActionPerformed

	private void formComponentShown(java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_formComponentShown
		featureThread.start();
	}// GEN-LAST:event_formComponentShown

	@Override
	public void notifyFeatureStatus(FeatureStatus event) {
		switch (event.getType()) {
		case FeatureStatus.STARTED:
			jpbProgress.setValue(0);
			break;
		case FeatureStatus.UPDATED:
			break;
		case FeatureStatus.COMPLETED:
			jpbProgress.setValue(100);
			EventQueue.invokeLater(() -> {
				featureThread.interrupt();
				this.dispose();
			});
			break;
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jbCancel;
	private javax.swing.JProgressBar jpbProgress;
	// End of variables declaration//GEN-END:variables
}
