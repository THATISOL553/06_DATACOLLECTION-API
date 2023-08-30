package com.dcapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcapi.binding.ChildBinding;
import com.dcapi.binding.ChildRequestBinding;
import com.dcapi.binding.EducationBinding;
import com.dcapi.binding.IncomeBinding;
import com.dcapi.binding.PlanSelectionBinding;
import com.dcapi.binding.Summary;
import com.dcapi.entity.ChildrenEntity;
import com.dcapi.entity.CitizenApiEntity;
import com.dcapi.entity.DcCasesEntity;
import com.dcapi.entity.EducationEntity;
import com.dcapi.entity.IncomeEntity;
import com.dcapi.entity.PlanEntity;
import com.dcapi.repository.ChildRepository;
import com.dcapi.repository.CitizenAppRepository;
import com.dcapi.repository.DcCasesRepository;
import com.dcapi.repository.EducationRepository;
import com.dcapi.repository.IncomeRepository;
import com.dcapi.repository.PlanRepository;

@Service
public class DcServiceImpl implements DCService {

	@Autowired
	private CitizenAppRepository citizenAppRepo;

	@Autowired
	private DcCasesRepository caseRepo;

	@Autowired
	private ChildRepository childRepo;

	@Autowired
	private IncomeRepository incomeRepo;

	@Autowired
	private EducationRepository eduRepo;

	@Autowired
	private PlanRepository planRepo;

	@Override
	public Long loadCaseNum(Integer appId) {
		Optional<CitizenApiEntity> findById = citizenAppRepo.findById(appId);

		if (findById.isPresent()) {
			DcCasesEntity entity = new DcCasesEntity();
			entity.setAppId(appId);
			entity = caseRepo.save(entity);

			return entity.getCaseNum();
		}

		return 0l;
	}

	@Override
	public Map<Integer, String> getPlanNames() {
		List<PlanEntity> entities = planRepo.findAll();

		Map<Integer, String> plansMap = new HashMap<>();

		for (PlanEntity entity : entities) {
			plansMap.put(entity.getPlanId(), entity.getPlanName());
		}

		return plansMap;
	}

	@Override
	public Long savePlanSelection(PlanSelectionBinding planSelectionBinding) {
		Optional<DcCasesEntity> findById = caseRepo.findById(planSelectionBinding.getCaseNum());
		if (findById.isPresent()) {
			DcCasesEntity entity = findById.get();
			entity.setPlanId(planSelectionBinding.getPlanId());
			caseRepo.save(entity);
			return planSelectionBinding.getCaseNum();
		}
		return null;
	}

	@Override
	public Long saveIncomeData(IncomeBinding incomeBinding) {
		IncomeEntity entity = new IncomeEntity();
		BeanUtils.copyProperties(incomeBinding, entity);
		incomeRepo.save(entity);
		return incomeBinding.getCaseNum();
	}

	@Override
	public Long saveEducation(EducationBinding eduBinding) {
		EducationEntity entity = new EducationEntity();
		BeanUtils.copyProperties(eduBinding, entity);
		eduRepo.save(entity);
		return eduBinding.getCaseNum();
	}

	@Override
	public Long saveChildrenData(ChildRequestBinding childRequestBinding) {
		List<ChildBinding> childs = childRequestBinding.getChilds();
		Long caseNum = childRequestBinding.getCaseNum();

		for (ChildBinding childBinding : childs) {
			ChildrenEntity entity = new ChildrenEntity();
			BeanUtils.copyProperties(childBinding, entity);
			entity.setCaseNum(caseNum);
			childRepo.save(entity);
		}
		return childRequestBinding.getCaseNum();
	}

	@Override
	public Summary getSummary(Long caseNum) {
		String planName = "";
		IncomeEntity incomeEntity = incomeRepo.findByCaseNum(caseNum);

		EducationEntity educationEntity = eduRepo.findByCaseNum(caseNum);

		List<ChildrenEntity> childEntities = childRepo.findByCaseNum(caseNum);

		Optional<DcCasesEntity> findById = caseRepo.findById(caseNum);
		if (findById.isPresent()) {
			Integer planId = findById.get().getPlanId();
			Optional<PlanEntity> plan = planRepo.findById(planId);
			if (plan.isPresent()) {
				planName = plan.get().getPlanName();
			}
		}
		// Set Data into Summary Object

		Summary summary = new Summary();
		summary.setPlanName(planName);

		EducationBinding educationBinding = new EducationBinding();
		BeanUtils.copyProperties(educationEntity, educationBinding);
		summary.setEducation(educationBinding);
		
		IncomeBinding incomeBinding = new IncomeBinding();
		BeanUtils.copyProperties(incomeEntity, incomeBinding);
		summary.setIncome(incomeBinding);
		
		List<ChildBinding> childs = new ArrayList<>();
		for(ChildrenEntity entity : childEntities) {
			ChildBinding childBinding = new ChildBinding();
			BeanUtils.copyProperties(entity, childBinding);
			childs.add(childBinding);
		}
		
		summary.setChildrens(childs);
		return summary;
	}

}
