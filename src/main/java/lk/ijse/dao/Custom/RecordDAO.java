package lk.ijse.dao.Custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.RecordDto;

public interface RecordDAO extends CrudDAO<RecordDto> {

    String splitRecordID(String currentID);
}
