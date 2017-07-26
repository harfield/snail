package com.harfield.snail.tool.kafka;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;

/**
 * Created by harfield on 2017/4/17.
 */
public class Crawler {
    public static void main(String[] args) throws IOException {
        WebClient client = new WebClient(BrowserVersion.CHROME);
//        client.getOptions().setUseInsecureSSL(true);
        HtmlPage page = client.getPage("http://admonitor.miaozhen.com/login");

        HtmlElement username = page.getElementByName("username");

        username.type("fanwei_DSP");

        HtmlElement pwd = page.getElementByName("password");

        pwd.type("4Uwo408YEf35KJg");

        DomElement submit = page.getElementById("submit");
        submit.click();
        System.out.print(page.getWebResponse().getContentAsString());
    }
}
