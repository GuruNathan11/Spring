package com.MHC.Project.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.MHC.Project.Model.Config;

public interface ConfigRepository extends MongoRepository<Config, String>{

	List<Config> findByPID(String pID);
	boolean existsByPID(String pID);
	List<Config> findByPIDIn(List<String> asList);
	List<Config> findByQ15Slot(String q15Slot);
	void deleteByPID(String pID);
	Optional<Config> findByPIDAndQ15DateAndQ15Time(String pID, String Q15Date, String Q15Time);
	List<Config> findByQ15SlotAndQ15Date(String q15Slot, String q15Date);
	List<Config> findByQ15SlotAndQ15DateAndTimestampCreatedAtNotNull(String q15Slot, String q15Date);
	List<Config> findByPIDAndQ15Date(String pID, String date);
//	List<Config> findByPidAndQ15Date(String pid, String format);
	List<Config> findByEnteredByAndQ15Date(String sid, String format);
	List<Config> findByPIDAndQ15DateBetween(String pid, String startD, String endD);
	List<Config> findByEnteredByAndQ15DateBetween(String sid, String startD, String endD);
	List<Config> findByPIDAndEnteredByAndQ15DateBetween(String pid, String enteredBy, String format, String format2);
	List<Config> findByPIDAndEnteredBy(String pid, String enteredBy);
	List<Config> findByEnteredBy(String enteredBy);
	List<Config> findByQ15DateBetween(String format, String format2);
//	List<Config> findByFilterCriteria(String pid, String enteredBy, LocalDate parsedStartDate, LocalDate parsedEndDate);
	List<Config> findByQ15Date(String startDate);
	List<Config> findByShiftIncharge(String shiftIncharge);
	List<Config> findByShift(String shift);
	List<Config> findByShiftInchargeAndQ15Date(String shiftIncharge, String startDate);
	List<Config> findByShiftAndQ15Date(String shift, String startDate);
	List<Config> findByShiftAndShiftIncharge(String shift, String shiftIncharge);
	List<Config> findByQ15SlotAndShiftIncharge(String slot, String shiftIncharge);
	List<Config> findByPIDAndEnteredByAndQ15SlotAndShiftAndShiftInchargeAndQ15DateBetween(String pid, String enteredBy,
			String slot, String shift, String shiftIncharge, String format, String format2);
	List<Config> findByQ15SlotAndQ15DateBetween(String slot, String format, String format2);
	List<Config> findByShiftAndQ15DateBetween(String shift, String format, String format2);
	List<Config> findByShiftInchargeAndQ15DateBetween(String shiftIncharge, String format, String format2);
	List<Config> findByPIDAndEnteredByAndQ15SlotAndShiftAndShiftInchargeAndQ15Date(String pid, String enteredBy,
			String slot, String shift, String shiftIncharge, String startDate);
	List<Config> findByPIDAndEnteredByAndQ15SlotAndShiftAndShiftIncharge(String pid, String enteredBy, String slot,
			String shift, String shiftIncharge);
	List<Config> findByEnteredByAndQ15SlotAndShiftAndShiftInchargeAndQ15Date(String enteredBy, String slot,
			String shift, String shiftIncharge, String startDate);
	List<Config> findByPIDAndEnteredByAndQ15SlotAndShift(String pid, String enteredBy, String slot, String shift);
	List<Config> findByPIDAndEnteredByAndQ15SlotAndShiftIncharge(String pid, String enteredBy, String slot,
			String shiftIncharge);
	List<Config> findByPIDAndEnteredByAndQ15SlotAndQ15Date(String pid, String enteredBy, String slot, String startDate);
	List<Config> findByEnteredByAndQ15SlotAndShiftAndShiftIncharge(String enteredBy, String slot, String shift,
			String shiftIncharge);
	List<Config> findByQ15SlotAndShiftAndShiftInchargeAndQ15Date(String slot, String shift, String shiftIncharge,
			String startDate);
	List<Config> findByPIDAndEnteredByAndQ15Slot(String pid, String enteredBy, String slot);
	List<Config> findByPIDAndEnteredByAndShift(String pid, String enteredBy, String shift);
	List<Config> findByPIDAndEnteredByAndShiftIncharge(String pid, String enteredBy, String shiftIncharge);
	List<Config> findByPIDAndEnteredByAndQ15Date(String pid, String enteredBy, String startDate);
	List<Config> findByPIDAndQ15SlotAndShift(String pid, String slot, String shift);
	List<Config> findByPIDAndQ15SlotAndShiftIncharge(String pid, String slot, String shiftIncharge);
	List<Config> findByPIDAndQ15SlotAndQ15Date(String pid, String slot, String startDate);
	List<Config> findByPIDAndShiftAndShiftIncharge(String pid, String shift, String shiftIncharge);
	List<Config> findByPIDAndShiftAndQ15Date(String pid, String shift, String startDate);
	List<Config> findByPIDAndShiftInchargeAndQ15Date(String pid, String shiftIncharge, String startDate);
	List<Config> findByEnteredByAndQ15SlotAndShift(String enteredBy, String slot, String shift);
	List<Config> findByEnteredByAndQ15SlotAndShiftIncharge(String enteredBy, String slot, String shiftIncharge);
	List<Config> findByEnteredByAndQ15SlotAndQ15Date(String enteredBy, String slot, String startDate);
	List<Config> findByEnteredByAndShiftAndShiftIncharge(String enteredBy, String shift, String shiftIncharge);
	List<Config> findByEnteredByAndShiftAndQ15Date(String enteredBy, String shift, String startDate);
	List<Config> findByEnteredByAndShiftInchargeAndQ15Date(String enteredBy, String shiftIncharge, String startDate);
	List<Config> findByQ15SlotAndShiftAndShiftIncharge(String slot, String shift, String shiftIncharge);
	List<Config> findByQ15SlotAndShiftInchargeAndQ15Date(String slot, String shiftIncharge, String startDate);
	List<Config> findByShiftAndShiftInchargeAndQ15Date(String shift, String shiftIncharge, String startDate);
	List<Config> findByPIDAndQ15Slot(String pid, String slot);
	List<Config> findByPIDAndShift(String pid, String shift);
	List<Config> findByPIDAndShiftIncharge(String pid, String shiftIncharge);
	List<Config> findByEnteredByAndQ15Slot(String enteredBy, String slot);
	List<Config> findByEnteredByAndShift(String enteredBy, String shift);
	List<Config> findByEnteredByAndShiftIncharge(String enteredBy, String shiftIncharge);
	List<Config> findByQ15SlotAndShift(String slot, String shift);
	boolean existsByQ15Date(String q15Date);
	void deleteByQ15Date(String q15Date);

}
