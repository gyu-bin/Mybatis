package com.example.logistics.production.applicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import com.example.logistics.production.dao.MpsDAO;
import com.example.logistics.production.dao.MrpDAO;
import com.example.logistics.production.dao.MrpGatheringDAO;
import com.example.logistics.production.to.MrpGatheringTO;
import com.example.logistics.production.to.MrpTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MrpApplicationServiceImpl implements MrpApplicationService {

	// DAO 참조변수 선언
	@Autowired
	private MpsDAO mpsDAO;
	@Autowired
	private MrpDAO mrpDAO;
	@Autowired
	private MrpGatheringDAO mrpGatheringDAO;

	public ArrayList<MrpTO> searchMrpList(String mrpGatheringStatusCondition) {

		HashMap<String, String> param = new HashMap<>();
		param.put("mrpGatheringStatusCondition", mrpGatheringStatusCondition);

		return mrpDAO.selectMrpListAll(param);
	}

	public ArrayList<MrpTO> searchMrpList(String dateSearchCondtion, String startDate, String endDate) {
		HashMap<String, String> param = new HashMap<>();
		param.put("dateSearchCondtion", dateSearchCondtion);
		param.put("startDate", startDate);
		param.put("endDate", endDate);

		return mrpDAO.selectMrpList(param);

	}

	public ArrayList<MrpTO> searchMrpListAsMrpGatheringNo(String mrpGatheringNo) {
		ArrayList<MrpTO> mrpList = null;
		mrpList = mrpDAO.selectMrpListAsMrpGatheringNo(mrpGatheringNo);

		return mrpList;

	}

	public ArrayList<MrpGatheringTO> searchMrpGatheringList(String dateSearchCondtion, String startDate,
															String endDate) {

		ArrayList<MrpGatheringTO> mrpGatheringList = null;

		HashMap<String, String> param = new HashMap<>();
		param.put("dateSearchCondtion", dateSearchCondtion);
		param.put("startDate", startDate);
		param.put("endDate", endDate);

		mrpGatheringList = mrpGatheringDAO.selectMrpGatheringList(param);

		for (MrpGatheringTO bean : mrpGatheringList) {

			bean.setMrpTOList(mrpDAO.selectMrpListAsMrpGatheringNo(bean.getMrpGatheringNo()));

		}

		return mrpGatheringList;


	}

	public HashMap<String, Object> openMrp(ArrayList<String> mpsNoArr) {
		HashMap<String, Object> resultMap = new HashMap<>();

		String mpsNoList = mpsNoArr.toString().replace("[", "").replace("]", "");
		HashMap<String, String> param = new HashMap<>();
		param.put("mpsNoList", mpsNoList);

		mrpDAO.openMrp(param);

		resultMap.put("gridRowJson", param.get("RESULT"));
		resultMap.put("errorCode", param.get("ERROR_CODE"));
		resultMap.put("errorMsg", param.get("ERROR_MSG"));

		return resultMap;

	}

	public HashMap<String, Object> registerMrp(String mrpRegisterDate, ArrayList<MrpTO> newMrpList) {
		HashMap<String, Object> resultMap = null;
		int i = mrpDAO.selectMrpCount(mrpRegisterDate);
		System.out.println(i);
		// 새로운 MRP 일련번호 양식 생성 : 등록일자 '2018-01-01' => 일련번호 'RP20180101-'
		StringBuffer newMrpNo = new StringBuffer();
		newMrpNo.append("RP"); //RP
		newMrpNo.append(mrpRegisterDate.replace("-", "")); // RP20200427
		newMrpNo.append("-"); //RP20200427-

		// 주생산계획번호를 담을 HashSet : 중복된 번호도 하나만 입력됨
		HashSet<String> mpsNoList = new HashSet<>();

		for (MrpTO bean : newMrpList) {

			bean.setMrpNo(newMrpNo.toString() + String.format("%03d", i++));
			//3자리로 일련번호 표현하고싶을때 사용. RP20200427-001,RP20200427-002,RP20200427-003,
			//클래스 주소로 공유되기때문에 bean에 셋팅해도 newMrpList에 셋팅
			bean.setStatus("INSERT");
			mpsNoList.add(bean.getMpsNo());

		}

		// 새로운 MRP 빈을 batchListProcess 로 테이블에 저장
		System.out.println("#####newMrpList#####" + newMrpList);
		resultMap = batchMrpListProcess(newMrpList);

		// 생성된 MRP 일련번호를 저장할 TreeSet
		TreeSet<String> mrpNoSet = new TreeSet<>();

		// "INSERT" 목록에 새로 생성된 MRP 일련번호들이 있음, ArrayList 로 형변환
		@SuppressWarnings("unchecked")
		ArrayList<String> mrpNoArr = (ArrayList<String>) resultMap.get("INSERT");

		for (String mrpNo : mrpNoArr) {
			mrpNoSet.add(mrpNo); // ArrayList 의 MRP 일련번호들을 TreeSet 에 저장

		}
		//pollFirst -> TreeSet객체에 저장된 첫번째 값을 반환하고 TreeSet객체에서 그 값을 지웁니다
		resultMap.put("firstMrpNo", mrpNoSet.pollFirst()); // 최초 MRP 일련번호를 결과 Map 에 저장
		resultMap.put("lastMrpNo", mrpNoSet.pollLast()); // 마지막 MRP 일련번호 결과 Map 에 저장

		// MPS 테이블에서 해당 mpsNo 의 MRP 적용상태를 "Y" 로 변경
		for (String mpsNo : mpsNoList) {

			HashMap<String, String> param = new HashMap<>();
			param.put("mpsNo", mpsNo);
			param.put("mrpStatus", "Y");
			mpsDAO.changeMrpApplyStatus(param);

		}

		// MRP 적용상태를 "Y" 로 변경한 주생산계획번호들을 결과 Map 에 저장
		resultMap.put("changeMrpApplyStatus", mpsNoList.toString().replace("[", "").replace("]", ""));

		return resultMap;
	}

	public HashMap<String, Object> batchMrpListProcess(ArrayList<MrpTO> mrpTOList) {
		HashMap<String, Object> resultMap = new HashMap<>();

		ArrayList<String> insertList = new ArrayList<>();
		ArrayList<String> updateList = new ArrayList<>();
		ArrayList<String> deleteList = new ArrayList<>();

		for (MrpTO bean : mrpTOList) {

			String status = bean.getStatus();

			switch (status) {

				case "INSERT":

					// dao 파트 시작
					mrpDAO.insertMrp(bean);
					// dao 파트 끝

					insertList.add(bean.getMrpNo());

					break;

				case "UPDATE":

					// dao 파트 시작
					mrpDAO.updateMrp(bean);
					// dao 파트 끝

					updateList.add(bean.getMrpNo());

					break;

				case "DELETE":

					// dao 파트 시작
					mrpDAO.deleteMrp(bean);
					// dao 파트 끝

					deleteList.add(bean.getMrpNo());

					break;

			}

		}

		resultMap.put("INSERT", insertList);
		resultMap.put("UPDATE", updateList);
		resultMap.put("DELETE", deleteList);

		return resultMap;
	}

	public ArrayList<MrpGatheringTO> getMrpGathering(ArrayList<String> mrpNoArr) {
		ArrayList<MrpGatheringTO> mrpGatheringList = null;
		// mrp번호 배열 [mrp번호,mrp번호, ...] => "mrp번호,mrp번호, ..." 형식의 문자열로 변환
		String mrpNoList = mrpNoArr.toString().replace("[", "").replace("]", "");
		System.out.println("mrpNoArr = "+mrpNoArr);
		System.out.println("mrpNoList = "+mrpNoList);
		mrpGatheringList = mrpGatheringDAO.getMrpGathering(mrpNoList);

		return mrpGatheringList;

	}

	public HashMap<String, Object> registerMrpGathering(
			String mrpGatheringRegisterDate,ArrayList<MrpGatheringTO> newMrpGatheringList, HashMap<String, String> mrpNoAndItemCodeMap) {
		//선택한날짜
		HashMap<String, Object> resultMap = null;

		// 소요량 취합일자로 새로운 소요량 취합번호 확인
		int i = mrpGatheringDAO.selectMrpGatheringCount(mrpGatheringRegisterDate); //선택한날짜
		/*
		 * ( itemCode : 새로운 mrpGathering 일련번호 ) 키/값 Map => itemCode 로 mrpNo 와
		 * mrpGatheringNo 를 매칭
		 */
		HashMap<String, String> itemCodeAndMrpGatheringNoMap = new HashMap<>();

		// 새로운 mrpGathering 일련번호 양식 생성 : 등록일자 '2020-04-28' => 일련번호 'MG20200428-'
		StringBuffer newMrpGatheringNo = new StringBuffer();
		newMrpGatheringNo.append("MG");
		newMrpGatheringNo.append(mrpGatheringRegisterDate.replace("-", ""));
		newMrpGatheringNo.append("-");

		int seq=mrpGatheringDAO.getMrpGatheringSeq();
		System.out.println(seq);
		// 새로운 mrpGathering 빈에 일련번호 입력 / status 를 "INSERT" 로 변경
		for (MrpGatheringTO bean : newMrpGatheringList) { //newMrpGatheringList : 소요량 취합결과 그리드에 뿌려진 데이터값

			bean.setMrpGatheringNo(newMrpGatheringNo.toString() + String.format("%03d", i++));
			bean.setStatus("INSERT"); //bean 즉, MrpGatheringTO의 클래스주소에 소요량취합번호 + INSERT set
			// mrpGathering 빈의 itemCode 와 mrpGatheringNo 를 키값:밸류값으로 map 에 저장
			bean.setMrpGatheringSeq(seq);
			itemCodeAndMrpGatheringNoMap.put(bean.getItemCode(), bean.getMrpGatheringNo());
		}

		// 새로운 mrpGathering 빈을 batchListProcess 로 테이블에 저장, 결과 Map 반환
		resultMap = batchMrpGatheringListProcess(newMrpGatheringList);//소요량 취합결과 그리드에 뿌려진 데이터값 //소요량취합번호, INSERT 추가됨

		// 생성된 mrp 일련번호를 저장할 TreeSet
		TreeSet<String> mrpGatheringNoSet = new TreeSet<>();

		// "INSERT_LIST" 목록에 "itemCode - mrpGatheringNo" 키/값 Map 이 있음
		@SuppressWarnings("unchecked")
		HashMap<String, String> mrpGatheringNoList = (HashMap<String, String>) resultMap.get("INSERT_MAP");//key(ItemCode):value(소요량취합번호)

		for (String mrpGatheringNo : mrpGatheringNoList.values()) {
			mrpGatheringNoSet.add(mrpGatheringNo); // mrpGatheringNoList 의 mrpGathering 일련번호들을 TreeSet 에 저장

		}

		resultMap.put("firstMrpGatheringNo", mrpGatheringNoSet.pollFirst()); // 최초 mrpGathering 일련번호를 결과 Map 에 저장
		resultMap.put("lastMrpGatheringNo", mrpGatheringNoSet.pollLast()); // 마지막 mrpGathering 일련번호를 결과 Map 에 저장

		// MRP 테이블에서 해당 mrpNo 의 mrpGatheringNo 저장, 소요량취합 적용상태를 "Y" 로 변경
		// itemCode 로 mrpNo 와 mrpGatheringNo 를 매칭시킨다
		for (String mrpNo : mrpNoAndItemCodeMap.keySet()) {

			String itemCode = mrpNoAndItemCodeMap.get(mrpNo);
			String mrpGatheringNo = itemCodeAndMrpGatheringNoMap.get(itemCode);
			HashMap<String, String> param = new HashMap<>();
			param.put("mrpNo", mrpNo);
			param.put("mrpGatheringNo", mrpGatheringNo);
			param.put("mrpGatheringStatus", "Y");
			mrpDAO.changeMrpGatheringStatus(param); ////////

		}

		// MRP 적용상태를 "Y" 로 변경한 MRP 번호들을 결과 Map 에 저장
		resultMap.put("changeMrpGatheringStatus",
				mrpNoAndItemCodeMap.keySet().toString().replace("[", "").replace("]", ""));

		StringBuffer sb = new StringBuffer();

		//사진 캡쳐할려고 코드 분리 시켜놓음, mrpGatheringNoList.values()로 돌리는 첫번째 for문이랑 합쳐야함
		for(String mrpGatheringNo : mrpGatheringNoList.values()) {
			sb.append(mrpGatheringNo);
			sb.append(",");
		}

		sb.delete(sb.toString().length()-1, sb.toString().length());

		System.out.println("sb");
		System.out.println(sb.toString());

		HashMap<String, String> parameter = new HashMap<>();
		parameter.put("mrpGatheringNoList", sb.toString());
		mrpGatheringDAO.updateMrpGatheringContract(parameter);

		return resultMap;
	}

	public HashMap<String, Object> batchMrpGatheringListProcess(ArrayList<MrpGatheringTO> mrpGatheringTOList) {
		HashMap<String, Object> resultMap = new HashMap<>();

		HashMap<String, String> insertListMap = new HashMap<>(); // "itemCode : mrpGatheringNo" 의 맵
		ArrayList<String> insertList = new ArrayList<>();
		ArrayList<String> updateList = new ArrayList<>();
		ArrayList<String> deleteList = new ArrayList<>();

		for (MrpGatheringTO bean : mrpGatheringTOList) {//소요량 취합결과 그리드에 뿌려진 데이터값

			String status = bean.getStatus();

			switch (status) {

				case "INSERT":

					mrpGatheringDAO.insertMrpGathering(bean);

					insertList.add(bean.getMrpGatheringNo());
					//소요량취합번호 추가
					insertListMap.put(bean.getItemCode(), bean.getMrpGatheringNo());
					//map에 key(ItemCode) : value(getMrpGatheringNo)
					break;

				case "UPDATE":

					mrpGatheringDAO.updateMrpGathering(bean);

					updateList.add(bean.getMrpGatheringNo());

					break;

				case "DELETE":

					mrpGatheringDAO.deleteMrpGathering(bean);

					deleteList.add(bean.getMrpGatheringNo());

					break;

			}

		}

		resultMap.put("INSERT_MAP", insertListMap); //key(ItemCode) : value(getMrpGatheringNo)
		resultMap.put("INSERT", insertList); //소요량취합번호
		resultMap.put("UPDATE", updateList);
		resultMap.put("DELETE", deleteList);

		return resultMap;
	}

}
