 
 import java.util.LinkedHashMap; 
 import org.springframework.batch.item.file.LineMapper; 
 import org.springframework.batch.item.file.mapping.JsonLineMapper; 
  public class JsonToCsvLineMapper implements LineMapper<DynamicJsonInVO> { 
 private JsonLineMapper delegate; 
 public JsonLineMapper getDelegate() { 
 	return delegate; 
 } 
 public void setDelegate(JsonLineMapper delegate) { 
 this.delegate = delegate; 
 } 
 public DynamicJsonInVO mapLine(String line, int lineNumber) throws Exception { 
 LinkedHashMap<String, Object> recordAsMap = (LinkedHashMap<String, Object>) delegate.mapLine(line, lineNumber); 
 return DynamicJsonInVO.setAllFields(recordAsMap); 
 }}