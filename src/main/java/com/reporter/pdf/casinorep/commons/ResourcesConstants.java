package com.reporter.pdf.casinorep.commons;


public class ResourcesConstants {
	public static final String PDF_TYPE= "PDF";
	public static final String DOT_CHAR= ".";
	public static final String ERROR= "ERROR";
	public static final String FORMAT_IMG_ERROR_MSG= "No se puede procesar este tipo de imagen: ";
	public static final String OK= "OK";
	public static final String SIMPLE_TITTLE= "Reporte Fotográfico";
	public static final String DATED_TITTLE= "Alfombras %Month\sYYYY%";
	public static final String SIMPLE_FOOTER= "Reporte Alfombras %No%";
	protected static final String PDF_EXT= ".pdf";
	public static final String SETTED_NAME= "%SETTEDNAME%";
	public static final String SETTED_DATE= "%SETTEDDATE%";
	protected static final String BASE_FILENAME= "casino-pdf-report";
	public static final String LOGO_ONE= "asecco";
	public static final String LOGO_TWO= "limpmex";
	
	public static ValidImageFormats ACCEPTED_IMAGE_FORMATS;
	
	public enum ValidImageFormats {
	    JPEG (".jpeg"), 
	    JPG (".jpg"), 
	    PNG (".png");

		private final String pictureFormat;
		
		ValidImageFormats(String pictureFormat) {
			this.pictureFormat = pictureFormat;
		}

	    private String pictureFormat() { return pictureFormat; }
	    
	    public static Boolean evalTypes(String incomingPictureType) {
	        for (ValidImageFormats picType : ValidImageFormats.values()) {
	        	if(picType.pictureFormat().equals(incomingPictureType)){
	        		return true;
	        	}
	        }
	        return false;
	    }
		
	}
	public enum ValidDependencyFormats {
	    DPDCY_00 ("las barreras","asecco"),
	    DPDCY_01 ("laz barreras","asecco"),
	    DPDCY_02 ("laz barreraz","asecco"),
	    DPDCY_03 ("hipodromo","asecco"), 
	    DPDCY_04 ("hipódromo","asecco"), 
	    DPDCY_05 ("rosarito","limpmex"), 
	    DPDCY_06 ("benito juarez","limpmex"), 
	    DPDCY_07 ("benito juárez","limpmex"), 
	    DPDCY_08 ("centro civico","limpmex"),
	    DPDCY_09 ("centro cívico","limpmex"),
	    DPDCY_10 ("civico","limpmex"),
	    DPDCY_11 ("civíco","limpmex"),
	    DPDCY_12 ("lazaro cardenas","limpmex"),
	    DPDCY_13 ("lázaro cardenas","limpmex"),
	    DPDCY_14 ("lazaro cárdenas","limpmex"),
	    DPDCY_15 ("lázaro cárdenas","limpmex");

		private final String subsidiaryName;
		private final String regionDependency;
		
		ValidDependencyFormats(String subsidiaryName, String regionDependency) {
			this.subsidiaryName = subsidiaryName;
			this.regionDependency = regionDependency;
		}

	    private String subsidiaryName() { return subsidiaryName; }
	    private String regionDependency() { return regionDependency; }
	    
	    public static String evalDependencyLogo(String incomingDependency) {
	        for (ValidDependencyFormats logoType : ValidDependencyFormats.values()) {
	        	if(logoType.subsidiaryName().equals(incomingDependency)){
	        		return logoType.regionDependency();
	        	}
	        }
	        return null;
	    }
		
	}
	public static CodeNumberFormats STATUS_CODE_FORMATS;
	
	private enum CodeNumberFormats {
	    OK ("00"), 
	    BAD_USE ("01"), 
	    PROCESS ("02"), 
	    INTERNAL ("03");
		
	    private final String code;
	    
		CodeNumberFormats(String code){
			this.code = code;
		}
		
	    private String code() { return code; }

	}
	
	public static String getCompleteFormatName() {
		return BASE_FILENAME.concat("-"+SETTED_NAME.concat("-"+SETTED_DATE.concat(PDF_EXT)));
	}
}
