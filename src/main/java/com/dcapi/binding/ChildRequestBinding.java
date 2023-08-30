package com.dcapi.binding;

import java.util.List;
import com.dcapi.entity.ChildrenEntity;
import lombok.Data;

@Data
public class ChildRequestBinding {
	
	private Long CaseNum;
	
	private List<ChildBinding> childs;

}
