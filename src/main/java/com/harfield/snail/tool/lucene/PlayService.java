package com.harfield.snail.tool.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by harfield on 2017/2/3.
 */
public class PlayService {
    private static String INDEXPATH="D:/Lucene/Index/" + PlayService.class.getSimpleName() + "/";
//    public static boolean createIndex(List<Play> playList){
//        try
//        {
//            Directory directory = FSDirectory.getDirectory(INDEXPATH, true);
//            IndexWriter indexWriter = new IndexWriter(directory, new StandardAnalyzer(),true);
//
//            long begin = new Date().getTime();
//            for(Play art: playList)
//            {
//                Document doc = new Document();
//                String title = art.getPlayTitle() == null ? "" : art.getPlayTitle().trim();
//                String content = art.getPlayUrl() == null ? "" : art.getPlayUrl();
//                String tag = art.getPlayId() + "";
//                doc.add(new Field("title", title, true, true, true));//enum
//                doc.add(new Field("content", content, true, true, true));
//                doc.add(new Field("tag", tag, true, true, true));
//                indexWriter.addDocument(doc);
//            }
//            long end = new Date().getTime();
//            System.out.println(">>> 1.存入索引完毕.. 共花费：" + (end - begin) +"毫秒...");
//
//            indexWriter.optimize();
//            indexWriter.close();
//            return true;
//
//        }catch(Exception e){
//            e.printStackTrace();
//            return false;
//        }
//
//    }
//    public static void searchIndex(String query) throws IOException, ParseException {
//
//        List<Play> qlist = new ArrayList<Play>();
//        String fieldName = "title";
//        IndexSearcher indexSearcher = new IndexSearcher(INDEXPATH);
//
//        //QueryParser parser = new QueryParser(fieldName, analyzer); //单 key 搜索
//        //Query queryOBJ = parser.parse(query);
//        System.out.println(">>> 2.开始读取索引... ... 通过关键字：【 "+ query +" 】");
//        long begin = new Date().getTime();
//
//        //下面的是进行title,content 两个范围内进行收索.
//        Query queryOBJ = MultiFieldQueryParser.parse(query, new String[]{"title","content"},
//                new int[]{MultiFieldQueryParser.NORMAL_FIELD,MultiFieldQueryParser.NORMAL_FIELD},
//                new StandardAnalyzer());//parser.parse(query);
//        Filter filter = null;
//
//        //################# 搜索相似度最高的记录 ###################
//        TopDocs topDocs = indexSearcher.search(queryOBJ, filter, 1000);
//        //TopDocs topDocs = indexSearcher.search(queryOBJ , 10000);
//        System.out.println("*** 共匹配：" + topDocs.totalHits + "个 ***");
//
//        for (ScoreDoc doc: topDocs.scoreDocs) {
//            Document targetDoc = indexSearcher.doc(doc.doc);
//            System.out.println(targetDoc.get(fieldName));
////            System.out.println(targetDoc.fields());
//        }
//        indexSearcher.close();


//    }
//    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, ParseException {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://10.215.28.40/pgc?useUnicode=true&characterEncoding=UTF-8", //
//                "vanee", //
//                "hMbKcEJAw" //
//        );
//        long start = System.currentTimeMillis();
//        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vanee_pgc_play limit 10000");
//        ResultSet resultSet = preparedStatement.executeQuery();
//        List<Play> playList = new ArrayList<Play>();
//        while(resultSet.next()){
//            Play play = new Play();
//            play.setPlayId(resultSet.getInt("play_id"));
//            play.setPlayTitle(resultSet.getString("play_title"));
//            play.setPlayUrl(resultSet.getString("play_url"));
//            playList.add(play);
//        }
//        DBUtils.close(resultSet);
//        DBUtils.close(preparedStatement);
//        DBUtils.close(connection);
//
//        System.out.println("time cost for query:" + (System.currentTimeMillis() - start));
//        createIndex(playList);
//        searchIndex("丈夫");
//    }
}
