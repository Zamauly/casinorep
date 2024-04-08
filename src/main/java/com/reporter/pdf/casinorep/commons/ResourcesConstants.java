package com.reporter.pdf.casinorep.commons;


public class ResourcesConstants {
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
	
	public static ValidImageFormats ACCEPTED_IMAGE_FORMATS;
	
	public enum ValidImageFormats {
	    JPEG ("jpeg"), 
	    JPG ("jpg"), 
	    PNG ("png");

		private final String pictureFormat;
		
		ValidImageFormats(String pictureFormat) {
			this.pictureFormat = pictureFormat;
		}

	    private String pictureFormat() { return pictureFormat; }
	    
	    public static Boolean evalTipes(String incomingPictureType) {
	        for (ValidImageFormats picType : ValidImageFormats.values()) {
	        	if(picType.pictureFormat().equals(incomingPictureType)){
	        		return true;
	        	}
	        }
	        return false;
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
