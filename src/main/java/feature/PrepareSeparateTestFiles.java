package feature;

import application.ApplicationData;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import domain.Indication;
import domain.IndicationsFile;
import domain.Sample;
import domain.SamplesFile;
import event.FeatureStatus;
import utility.StringDataFileWriter;

public class PrepareSeparateTestFiles extends Feature {

	final String indicationsFileName;

	final String samplesFileName;

	final String folderName;

	final long stimulusTime;

	final long triggerTime_average;

	final long interval_left;

	final long interval_right;

	final long duration_min;

	final long indicationsFileTimeStep;

	public PrepareSeparateTestFiles(ApplicationData data) {
		indicationsFileName = data.getIndicationsFileName();
		samplesFileName = data.getSamplesFileName();
		folderName = data.getOutputFolder();
		stimulusTime = data.getStimulusTime();
		triggerTime_average = data.getTriggerTimeAverage();
		interval_left = data.getIntervalLeft();
		interval_right = data.getIntervalRight();
		duration_min = data.getDurationMin();
		indicationsFileTimeStep = data.getIndicationsFileTimeStep();
	}

	@Override
	public void run() {
		notifyFeatureStatus(new FeatureStatus(FeatureStatus.STARTED));
		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdir();
		}

		try (SamplesFile samplesFile = new SamplesFile(samplesFileName)) {
			try (IndicationsFile indicationsFile = new IndicationsFile(indicationsFileName, indicationsFileTimeStep)) {
				while (samplesFile.hasNext()) {
					Sample sample = samplesFile.nextSample();
					long intervalTime_begin = sample.getStartTime() + stimulusTime;
					if (sample.hasTriggerTime()) {
						intervalTime_begin += sample.getTriggerTime();
					} else {
						intervalTime_begin += triggerTime_average;
					}
					intervalTime_begin -= interval_left;
					long intervalTime_end = intervalTime_begin + interval_left + interval_right;
					if (intervalTime_end > sample.getStartTime() + duration_min) {
						intervalTime_end = sample.getStartTime() + duration_min;
					}

					String testFileName = folderName + "//"
							+ String.format("Tr%04d_%02d.txt", sample.getNumber(), sample.getLabel());
					try (StringDataFileWriter testFile = new StringDataFileWriter(testFileName)) {
						while (indicationsFile.hasNext()) {
							Indication indication = indicationsFile.nextIndication();

							if (indication.getTime() > intervalTime_end) {
								break;
							}

							if (indication.getTime() >= intervalTime_begin) {
								testFile.writeLine(indication.toString());
							}
						}
					}
					notifyFeatureStatus(new FeatureStatus(FeatureStatus.UPDATED));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		notifyFeatureStatus(new FeatureStatus(FeatureStatus.COMPLETED));
	}

	public static void main(String args[]) {
		ApplicationData applicationData = new ApplicationData();
		applicationData.setSamplesFileName("data//BNDmetki.txt");
		applicationData.setIndicationsFileName("data//bnd.txt");
		applicationData.setIndicationsFileTimeStep(IndicationsFile.timeStep_500HZ);
		applicationData.setOutputFolder("data//bnd");

		applicationData.setStimulusTime(300);
		applicationData.setTriggerTimeAverage(450);
		applicationData.setIntervalLeft(204);
		applicationData.setIntervalRight(396);
		applicationData.setDurationMin(1984);

		PrepareSeparateTestFiles feature = new PrepareSeparateTestFiles(applicationData);
		feature.run();
	}
}
