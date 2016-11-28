package com.eden.garden;

import com.caseyjbrooks.eden.Eden;
import com.caseyjbrooks.eden.bible.Bible;
import com.caseyjbrooks.eden.bible.BibleList;
import com.caseyjbrooks.eden.bible.Reference;
import com.eden.americanbiblesociety.ABSBible;
import com.eden.americanbiblesociety.ABSBibleList;
import com.eden.digitalbibleplatform.DBPBible;
import com.eden.digitalbibleplatform.DBPBibleList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BibleController {

    @RequestMapping("/")
    public String getRepositories() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/bible")
    public Bible getBible(@RequestParam(value="service", defaultValue="abs") String service,
                          @RequestParam(value="id", defaultValue="eng-ESV") String id) {

        Eden.getInstance().getMetadata().put("ABS_ApiKey", "mDaM8REZFo6itplNpcv1ls8J5PkwEz1wbhJ7p9po");
        Eden.getInstance().getMetadata().put("DBT_ApiKey", "82cb00ba8a8917d05e27c99facb8ff96");

        if(service.toLowerCase().equals("abs")) {
            ABSBible myBible = new ABSBible();
            myBible.setId(id);
            myBible.download();

            return myBible;
        }
        else if(service.toLowerCase().equals("dbp")) {
            DBPBible myBible = new DBPBible();
            myBible.setId(id);
            myBible.download();

            return myBible;
        }

        return null;
    }

    @RequestMapping("/parse")
    public Reference parse(@RequestParam(value="service", defaultValue="abs") String service,
                          @RequestParam(value="id", defaultValue="eng-ESV") String id,
                       @RequestParam(value="input", defaultValue="") String input) {

        Eden.getInstance().getMetadata().put("ABS_ApiKey", "mDaM8REZFo6itplNpcv1ls8J5PkwEz1wbhJ7p9po");
        Eden.getInstance().getMetadata().put("DBT_ApiKey", "82cb00ba8a8917d05e27c99facb8ff96");

        if(service.toLowerCase().equals("abs")) {
            ABSBible myBible = new ABSBible();
            myBible.setId(id);
            myBible.download();

            return new Reference.Builder()
                .setBible(myBible)
                .parseReference(input)
                .create();
        }
        else if(service.toLowerCase().equals("dbp")) {
            DBPBible myBible = new DBPBible();
            myBible.setId(id);
            myBible.download();

            return new Reference.Builder()
                    .setBible(myBible)
                    .parseReference(input)
                    .create();
        }

        return null;
    }

    @RequestMapping("/bibles")
    public BibleList getBibleList(@RequestParam(value="service", defaultValue="abs") String service) {
        Eden.getInstance().getMetadata().put("ABS_ApiKey", "mDaM8REZFo6itplNpcv1ls8J5PkwEz1wbhJ7p9po");
        Eden.getInstance().getMetadata().put("DBT_ApiKey", "82cb00ba8a8917d05e27c99facb8ff96");

        if(service.toLowerCase().equals("abs")) {
            ABSBibleList bibleList = new ABSBibleList();
            bibleList.download();

            return bibleList;
        }
        else if(service.toLowerCase().equals("dbp")) {
            DBPBibleList bibleList = new DBPBibleList();
            bibleList.download();

            return bibleList;
        }

        return null;
    }
}
