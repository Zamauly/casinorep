package com.reporter.pdf.casinorep.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ResourcesProperties {
    private String rsc_path;
    private final BaseImages base_images = new BaseImages();
    private String documental_images;
    private String documental_pdfs;
	
	public String getDocumental_images() {
		return documental_images;
	}

	public void setDocumental_images(String documental_images) {
		this.documental_images = documental_images;
	}

	public String getDocumental_pdfs() {
		return documental_pdfs;
	}

	public void setDocumental_pdfs(String documental_pdfs) {
		this.documental_pdfs = documental_pdfs;
	}

	public BaseImages getBase_images() {
		return base_images;
	}

	public String getRsc_path() {
		return rsc_path;
	}

	public void setRsc_path(String rsc_path) {
		this.rsc_path = rsc_path;
	}

	public static class BaseImages {
	    private String path;
	    private String logo_aseco;
	    private String logo_limpo;
	    private List<String> files_base = new ArrayList<>();
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getLogo_aseco() {
			return logo_aseco;
		}
		public void setLogo_aseco(String logo_aseco) {
			this.logo_aseco = logo_aseco;
		}
		public String getLogo_limpo() {
			return logo_limpo;
		}
		public void setLogo_limpo(String logo_limpo) {
			this.logo_limpo = logo_limpo;
		}
		public List<String> getFiles_base() {
			return files_base;
		}
		public void setFiles_base(List<String> files_base) {
			this.files_base = files_base;
		}
	    
	}
	
}
