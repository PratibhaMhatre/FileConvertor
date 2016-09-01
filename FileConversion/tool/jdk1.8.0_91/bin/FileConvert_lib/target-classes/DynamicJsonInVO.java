 import java.util.LinkedHashMap; 
 import java.util.Set;  
  public class DynamicJsonInVO {
	private java.lang.String modified1;
	private java.lang.String identifier1;
	private java.lang.String brandNew;
	private java.lang.String Pratitest;
	private java.lang.String newcol;


	public java.lang.String getModified1() {
		return this.modified1;
	}
	public void setModified1(java.lang.String modified1) {
		this.modified1 = modified1;
	}

	public java.lang.String getIdentifier1() {
		return this.identifier1;
	}
	public void setIdentifier1(java.lang.String identifier1) {
		this.identifier1 = identifier1;
	}

	public java.lang.String getBrandNew() {
		return this.brandNew;
	}
	public void setBrandNew(java.lang.String brandNew) {
		this.brandNew = brandNew;
	}

	public java.lang.String getPratitest() {
		return this.Pratitest;
	}
	public void setPratitest(java.lang.String Pratitest) {
		this.Pratitest = Pratitest;
	}

	public java.lang.String getNewcol() {
		return this.newcol;
	}
	public void setNewcol(java.lang.String newcol) {
		this.newcol = newcol;
	}

	 public static DynamicJsonInVO setAllFields(LinkedHashMap<String, Object> valueMap) { 
		 DynamicJsonInVO tempDynamicObj = new DynamicJsonInVO();
		 Set<String> keys = valueMap.keySet(); 
		 if(valueMap.get("modified1")== null) { 
			 keys.remove("modified1");
			 tempDynamicObj.setModified1("null"); 
 		 } else { 
			 tempDynamicObj.setModified1(valueMap.get("modified1").toString()); 
 		 } 
		 if(valueMap.get("identifier1")== null) { 
			 keys.remove("identifier1");
			 tempDynamicObj.setIdentifier1("null"); 
 		 } else { 
			 tempDynamicObj.setIdentifier1(valueMap.get("identifier1").toString()); 
 		 } 
		 if(valueMap.get("brandNew")== null) { 
			 keys.remove("brandNew");
			 tempDynamicObj.setBrandNew("null"); 
 		 } else { 
			 tempDynamicObj.setBrandNew(valueMap.get("brandNew").toString()); 
 		 } 
		 if(valueMap.get("Pratitest")== null) { 
			 keys.remove("Pratitest");
			 tempDynamicObj.setPratitest("null"); 
 		 } else { 
			 tempDynamicObj.setPratitest(valueMap.get("Pratitest").toString()); 
 		 } 
		 if(valueMap.get("newcol")== null) { 
			 keys.remove("newcol");
			 tempDynamicObj.setNewcol("null"); 
 		 } else { 
			 tempDynamicObj.setNewcol(valueMap.get("newcol").toString()); 
 		 } 
 		 return tempDynamicObj; 
 } 
 }