package com.MHC.Project.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Vital")
public class Vital {
	
	@Id
	private String id;
	private String patientId;
	private String enteredDate;
	private BodyTemperature bodyTemperature;
	private PulseRate pulseRate;
	private HeartRate heartRate;
	private RespirationRate respirationRate;
	private BloodPressure bloodPressure;
	private BloodOxygen bloodOxygen;
	private Weight weight;
	private Height height;
	private BloodGlucoseLevel bloodGlucoseLevel;
	private CircumferenceOrGirth circumferenceOrGirth;
	private Pain pain;
	private PulseOximetry pulseOximetry; 
	private String lastVisit;
	private String comments;
	private String createdAt;
	private String updatedAt;
	
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public CircumferenceOrGirth getCircumferenceOrGirth() {
		return circumferenceOrGirth;
	}
	public void setCircumferenceOrGirth(CircumferenceOrGirth circumferenceOrGirth) {
		this.circumferenceOrGirth = circumferenceOrGirth;
	}
	public Pain getPain() {
		return pain;
	}
	public void setPain(Pain pain) {
		this.pain = pain;
	}
	public PulseOximetry getPulseOximetry() {
		return pulseOximetry;
	}
	public void setPulseOximetry(PulseOximetry pulseOximetry) {
		this.pulseOximetry = pulseOximetry;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getEnteredDate() {
		return enteredDate;
	}
	public void setEnteredDate(String enteredDate) {
		this.enteredDate = enteredDate;
	}
	public BodyTemperature getBodyTemperature() {
		return bodyTemperature;
	}
	public void setBodyTemperature(BodyTemperature bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}
	public PulseRate getPulseRate() {
		return pulseRate;
	}
	public void setPulseRate(PulseRate pulseRate) {
		this.pulseRate = pulseRate;
	}
	public HeartRate getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(HeartRate heartRate) {
		this.heartRate = heartRate;
	}
	public RespirationRate getRespirationRate() {
		return respirationRate;
	}
	public void setRespirationRate(RespirationRate respirationRate) {
		this.respirationRate = respirationRate;
	}
	public BloodPressure getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(BloodPressure bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public BloodOxygen getBloodOxygen() {
		return bloodOxygen;
	}
	public void setBloodOxygen(BloodOxygen bloodOxygen) {
		this.bloodOxygen = bloodOxygen;
	}
	public Weight getWeight() {
		return weight;
	}
	public void setWeight(Weight weight) {
		this.weight = weight;
	}
	public Height getHeight() {
		return height;
	}
	public void setHeight(Height height) {
		this.height = height;
	}
	public BloodGlucoseLevel getBloodGlucoseLevel() {
		return bloodGlucoseLevel;
	}
	public void setBloodGlucoseLevel(BloodGlucoseLevel bloodGlucoseLevel) {
		this.bloodGlucoseLevel = bloodGlucoseLevel;
	}
	
	public Vital (String id,String patientId,BodyTemperature bodyTemperature,PulseRate pulseRate,HeartRate heartRate,RespirationRate respirationRate,BloodPressure bloodPressure,
			BloodOxygen bloodOxygen,Height height, Weight weight,BloodGlucoseLevel bloodGlucoseLevel,CircumferenceOrGirth circumferenceOrGirth,
			Pain pain, PulseOximetry pulseOximetry,String lastVisit,String comments, String createdAt, String updatedAt) {
		
		this.id=id; 
		this.patientId=patientId;
		this.bodyTemperature=bodyTemperature;
		this.pulseRate=pulseRate;
		this.heartRate=heartRate;
		this.respirationRate=respirationRate;
		this.bloodPressure=bloodPressure;
		this.bloodOxygen=bloodOxygen;
		this.height=height;
		this.weight=weight;
		this.bloodGlucoseLevel=bloodGlucoseLevel;
		this.circumferenceOrGirth = circumferenceOrGirth;
		this.pain=pain;
		this.pulseOximetry=pulseOximetry;
		this.lastVisit= lastVisit;
		this.comments=comments;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Vital build(String id,String patientId, BodyTemperature bodyTemperature,PulseRate pulseRate,HeartRate heartRate,RespirationRate respirationRate,BloodPressure bloodPressure,
			BloodOxygen bloodOxygen,Height height, Weight weight,BloodGlucoseLevel bloodGlucoseLevel, CircumferenceOrGirth circumferenceOrGirth, Pain pain, PulseOximetry pulseOximetry, String lastVisit,String comments, String createdAt, String updatedAt) {
		 
		return new Vital(id,patientId,bodyTemperature,pulseRate,heartRate,respirationRate,bloodPressure,bloodOxygen,height,weight,bloodGlucoseLevel,circumferenceOrGirth,pain,pulseOximetry,lastVisit,comments, createdAt, updatedAt);
	}
	
	public void update(Vital vital)
	{
		this.setId(vital.getId());
		this.setPatientId(vital.getPatientId());
		this.setBodyTemperature(vital.getBodyTemperature());
		this.setPulseRate(vital.getPulseRate());
		this.setHeartRate(vital.getHeartRate());
		this.setRespirationRate(vital.getRespirationRate());
		this.setBloodPressure(vital.getBloodPressure());
		this.setBloodOxygen(vital.getBloodOxygen());
		this.setHeight(vital.getHeight());
		this.setWeight(vital.getWeight());
		this.setBloodGlucoseLevel(vital.getBloodGlucoseLevel());
		this.setCircumferenceOrGirth(vital.getCircumferenceOrGirth());
		this.setPain(vital.getPain());
		this.setPulseOximetry(vital.getPulseOximetry());
		this.setLastVisit(vital.getLastVisit());
		this.setComments(vital.getComments());
	}
	
	public String getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}

	public static class BodyTemperature
	{
		String measurementDT;
		String vitalMeasurementName;
		String value;
		String unit;
		
		BtQualifiers btQualifiers;
		
		public String getMeasurementDT() {
			return measurementDT;
		}
		public void setMeasurementDT(String measurementDT) {
			this.measurementDT = measurementDT;
		}
		public String getVitalMeasurementName() {
			return vitalMeasurementName;
		}
		public void setVitalMeasurementName(String vitalMeasurementName) {
			this.vitalMeasurementName = vitalMeasurementName;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public BtQualifiers getBtQualifiers() {
			return btQualifiers;
		}
		public void setBtQualifiers(BtQualifiers btQualifiers) {
			this.btQualifiers = btQualifiers;
		}
		public static class BtQualifiers
		{
			String location;

			public String getLocation() {
				return location;
			}
			public void setLocation(String location) {
				this.location = location;
			}
		}
	}
	public static class PulseRate
	{
		String measurementDT;
		String vitalMeasurementName;
		String value;
		String unit;
		PrQualifiers prQualifiers;
		
		public String getMeasurementDT() {
			return measurementDT;
		}
		public void setMeasurementDT(String measurementDT) {
			this.measurementDT = measurementDT;
		}
		public String getVitalMeasurementName() {
			return vitalMeasurementName;
		}
		public void setVitalMeasurementName(String vitalMeasurementName) {
			this.vitalMeasurementName = vitalMeasurementName;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public PrQualifiers getPrQualifiers() {
			return prQualifiers;
		}
		public void setPrQualifiers(PrQualifiers prQualifiers) {
			this.prQualifiers = prQualifiers;
		}
		
		public static class PrQualifiers
		{
			String location;
			String method;
			String position;
			String site;

			public String getLocation() {
				return location;
			}
			public void setLocation(String location) {
				this.location = location;
			}
			public String getMethod() {
				return method;
			}
			public void setMethod(String method) {
				this.method = method;
			}
			public String getPosition() {
				return position;
			}
			public void setPosition(String position) {
				this.position = position;
			}
			public String getSite() {
				return site;
			}
			public void setSite(String site) {
				this.site = site;
			}
		}
	}
	public static class HeartRate
	{
		String measurementDT;
		String vitalMeasurementName;
		String value;
		String unit;
		HrQualifiers hrQualifiers;
		
		public String getMeasurementDT() {
			return measurementDT;
		}
		public void setMeasurementDT(String measurementDT) {
			this.measurementDT = measurementDT;
		}
		public String getVitalMeasurementName() {
			return vitalMeasurementName;
		}
		public void setVitalMeasurementName(String vitalMeasurementName) {
			this.vitalMeasurementName = vitalMeasurementName;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public HrQualifiers getHrQualifiers() {
			return hrQualifiers;
		}
		public void setHrQualifiers(HrQualifiers hrQualifiers) {
			this.hrQualifiers = hrQualifiers;
		}
		public static class HrQualifiers
		{
			String location;
			String position;

			public String getLocation() {
				return location;
			}
			public void setLocation(String location) {
				this.location = location;
			}

		}
	}
	public static class RespirationRate
	{
		String measurementDT;
		String vitalMeasurementName;
		String value;
		String unit;
		RrQualifiers rrQualifiers;
		
		public String getMeasurementDT() {
			return measurementDT;
		}
		public void setMeasurementDT(String measurementDT) {
			this.measurementDT = measurementDT;
		}
		public String getVitalMeasurementName() {
			return vitalMeasurementName;
		}
		public void setVitalMeasurementName(String vitalMeasurementName) {
			this.vitalMeasurementName = vitalMeasurementName;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public RrQualifiers getRrQualifiers() {
			return rrQualifiers;
		}
		public void setRrQualifiers(RrQualifiers rrQualifiers) {
			this.rrQualifiers = rrQualifiers;
		}
		public static class RrQualifiers
		{
			String method;
			String position;
			
			public String getMethod() {
				return method;
			}
			public void setMethod(String method) {
				this.method = method;
			}
			public String getPosition() {
				return position;
			}
			public void setPosition(String position) {
				this.position = position;
			}
		}
	}
	public static class BloodPressure
	{
		String measurementDT;
		String vitalMeasurementName;
		String systolicValue;
		String diastolicValue;
		String unit;
		BpQualifiers bpQualifiers;
		
		public String getMeasurementDT() {
			return measurementDT;
		}
		public void setMeasurementDT(String measurementDT) {
			this.measurementDT = measurementDT;
		}
		public String getVitalMeasurementName() {
			return vitalMeasurementName;
		}
		public void setVitalMeasurementName(String vitalMeasurementName) {
			this.vitalMeasurementName = vitalMeasurementName;
		}
		public String getSystolicValue() {
			return systolicValue;
		}
		public void setSystolicValue(String systolicValue) {
			this.systolicValue = systolicValue;
		}
		public String getDiastolicValue() {
			return diastolicValue;
		}
		public void setDiastolicValue(String diastolicValue) {
			this.diastolicValue = diastolicValue;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public BpQualifiers getBpQualifiers() {
			return bpQualifiers;
		}
		public void setBpQualifiers(BpQualifiers bpQualifiers) {
			this.bpQualifiers = bpQualifiers;
		}
		public static class BpQualifiers
		{
			String cuffSize;
			String method;
			String position;

			public String getCuffSize() {
				return cuffSize;
			}
			public void setCuffSize(String cuffSize) {
				this.cuffSize = cuffSize;
			}
			public String getMethod() {
				return method;
			}
			public void setMethod(String method) {
				this.method = method;
			}
			public String getPosition() {
				return position;
			}
			public void setPosition(String position) {
				this.position = position;
			}
		}
	}
	public static class BloodOxygen
	{
		String measurementDT;
		String vitalMeasurementName;
		String value;
		String unit;
		BoQualifiers boQualifiers;
		
		public String getMeasurementDT() {
			return measurementDT;
		}
		public void setMeasurementDT(String measurementDT) {
			this.measurementDT = measurementDT;
		}
		public String getVitalMeasurementName() {
			return vitalMeasurementName;
		}
		public void setVitalMeasurementName(String vitalMeasurementName) {
			this.vitalMeasurementName = vitalMeasurementName;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public BoQualifiers getBoQualifiers() {
			return boQualifiers;
		}
		public void setBoQualifiers(BoQualifiers boQualifiers) {
			this.boQualifiers = boQualifiers;
		}
		public static class BoQualifiers
		{
			String method;
			
			public String getMethod() {
				return method;
			}
			public void setMethod(String method) {
				this.method = method;
			}
		}
	}
	public static class Weight
	{
		String measurementDT;
		String vitalMeasurementName;
		String value;
		String unit;
		WQualifiers wQualifiers;
		
		public String getMeasurementDT() {
			return measurementDT;
		}
		public void setMeasurementDT(String measurementDT) {
			this.measurementDT = measurementDT;
		}
		public String getVitalMeasurementName() {
			return vitalMeasurementName;
		}
		public void setVitalMeasurementName(String vitalMeasurementName) {
			this.vitalMeasurementName = vitalMeasurementName;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public WQualifiers getWQualifiers() {
			return wQualifiers;
		}
		public void setWQualifiers(WQualifiers wQualifiers) {
			this.wQualifiers = wQualifiers;
		}
		public static class WQualifiers
		{
			String method;
			String quality;
			
			public String getMethod() {
				return method;
			}
			public void setMethod(String method) {
				this.method = method;
			}
			public String getQuality() {
				return quality;
			}
			public void setQuality(String quality) {
				this.quality = quality;
			}
 		}
	}
	public static class Height
	{
		String measurementDT;
		String vitalMeasurementName;
		String value;
		String unit;
		HQualifiers hQualifiers;
		
		public String getMeasurementDT() {
			return measurementDT;
		}
		public void setMeasurementDT(String measurementDT) {
			this.measurementDT = measurementDT;
		}
		public String getVitalMeasurementName() {
			return vitalMeasurementName;
		}
		public void setVitalMeasurementName(String vitalMeasurementName) {
			this.vitalMeasurementName = vitalMeasurementName;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public HQualifiers getHQualifiers() {
			return hQualifiers;
		}
		public void setHQualifiers(HQualifiers hQualifiers) {
			this.hQualifiers = hQualifiers;
		}
		public static class HQualifiers
		{
			String quality;
			
			public String getQuality() {
				return quality;
			}
			public void setQuality(String quality) {
				this.quality = quality;
			}
		}
	}
	public static class BloodGlucoseLevel
	{
		String measurementDT;
		String vitalMeasurementName;
		String value;
		String unit;
		BgQualifiers bgQualifiers;
		
		public String getMeasurementDT() {
			return measurementDT;
		}
		public void setMeasurementDT(String measurementDT) {
			this.measurementDT = measurementDT;
		}
		public String getVitalMeasurementName() {
			return vitalMeasurementName;
		}
		public void setVitalMeasurementName(String vitalMeasurementName) {
			this.vitalMeasurementName = vitalMeasurementName;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public BgQualifiers getBgQualifiers() {
			return bgQualifiers;
		}
		public void setBgQualifiers(BgQualifiers bgQualifiers) {
			this.bgQualifiers = bgQualifiers;
		}
		public static class BgQualifiers
		{
			String location;
			String position;
			String quality;
			

			public String getLocation() {
				return location;
			}
			public void setLocation(String location) {
				this.location = location;
			}
			public String getPosition() {
				return position;
			}
			public void setPosition(String position) {
				this.position = position;
			}
			public String getQuality() {
				return quality;
			}
			public void setQuality(String quality) {
				this.quality = quality;
			}
			
		}
	}
	public static class CircumferenceOrGirth
	{
		String measurementDT;
		String vitalMeasurementName;
		String value;
		String unit;
		CgQualifiers cgQualifiers;
		
		public String getMeasurementDT() {
			return measurementDT;
		}

		public void setMeasurementDT(String measurementDT) {
			this.measurementDT = measurementDT;
		}

		public String getVitalMeasurementName() {
			return vitalMeasurementName;
		}

		public void setVitalMeasurementName(String vitalMeasurementName) {
			this.vitalMeasurementName = vitalMeasurementName;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public CgQualifiers getCgQualifiers() {
			return cgQualifiers;
		}

		public void setCgQualifiers(CgQualifiers cgQualifiers) {
			this.cgQualifiers = cgQualifiers;
		}

		public static class CgQualifiers{
			String site;
			String Location;
			
			public String getSite() {
				return site;
			}
			public void setSite(String site) {
				this.site = site;
			}
			public String getLocation() {
				return Location;
			}
			public void setLocation(String location) {
				Location = location;
			}
		}
	}
	public static class Pain{
		
		String measurementDT;
		String vitalMeasurementName;
		String value;
		String unit;
		
		public String getMeasurementDT() {
			return measurementDT;
		}
		public void setMeasurementDT(String measurementDT) {
			this.measurementDT = measurementDT;
		}
		public String getVitalMeasurementName() {
			return vitalMeasurementName;
		}
		public void setVitalMeasurementName(String vitalMeasurementName) {
			this.vitalMeasurementName = vitalMeasurementName;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
	}
	
	public static class PulseOximetry{
		String measurementDT;
		String vitalMeasurementName;
		String value;
		String unit;
		PoQualifiers poQualifiers;
		
		public String getMeasurementDT() {
			return measurementDT;
		}

		public void setMeasurementDT(String measurementDT) {
			this.measurementDT = measurementDT;
		}

		public String getVitalMeasurementName() {
			return vitalMeasurementName;
		}

		public void setVitalMeasurementName(String vitalMeasurementName) {
			this.vitalMeasurementName = vitalMeasurementName;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public PoQualifiers getPoQualifiers() {
			return poQualifiers;
		}

		public void setPoQualifiers(PoQualifiers poQualifiers) {
			this.poQualifiers = poQualifiers;
		}

		public static class PoQualifiers{ 
			String supplimentalOxygen;
			String flowRate;
			String o2concentration;
			String method;
			
			public String getSupplimentalOxygen() {
				return supplimentalOxygen;
			}
			public void setSupplimentalOxygen(String supplimentalOxygen) {
				this.supplimentalOxygen = supplimentalOxygen;
			}
			public String getFlowRate() {
				return flowRate;
			}
			public void setFlowRate(String flowRate) {
				this.flowRate = flowRate;
			}
			public String getO2concentration() {
				return o2concentration;
			}
			public void setO2concentration(String o2concentration) {
				this.o2concentration = o2concentration;
			}
			public String getMethod() {
				return method;
			}
			public void setMethod(String method) {
				this.method = method;
			}
		}
	}
}