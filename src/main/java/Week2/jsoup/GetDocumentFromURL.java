package Week2.jsoup;

import java.io.File;
import java.io.IOException;
import java.time.*;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetDocumentFromURL {

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://dantri.com.vn").get();
		System.out.println("Doc : " + doc);
		
		File f = new File(LocalDate.now() + ".html");
		FileUtils.writeStringToFile(f, doc.outerHtml(), "UTF-8");
	}

}