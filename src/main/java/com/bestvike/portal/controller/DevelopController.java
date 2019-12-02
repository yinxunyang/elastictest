package com.bestvike.portal.controller;

import com.bestvike.commons.support.ColumnDefine;
import com.bestvike.commons.support.TableDefine;
import com.bestvike.commons.util.EncryptUtil;
import com.bestvike.commons.util.ExcelUtil;
import com.bestvike.commons.util.StringUtil;
import com.bestvike.portal.data.LogFile;
import com.bestvike.portal.mq.Publisher;
import com.bestvike.portal.service.FileService;
import org.apache.ibatis.utils.CamelCaseUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/develop")
public class DevelopController extends BaseController {
    @Autowired
    private FileService fileService;
    @Autowired
    private Publisher sender;

    public DevelopController( ) {
        super("95");
    }

    @RequestMapping(value = "/importTable", method = RequestMethod.POST)
    public String importTable(@RequestBody LogFile logFile) throws IOException, InvalidFormatException, SAXException {
        if (!StringUtils.isEmpty(logFile.getFileSign())) {
            LogFile logFileDb = fileService.selectBySign(logFile.getFileSign());
            if (logFileDb != null) {
                Map<String, String> javaTypeMap = new HashMap<String, String>();
                javaTypeMap.put("number", "Integer");
                javaTypeMap.put("varchar2", "String");
                javaTypeMap.put("clob", "String");
                javaTypeMap.put("date", "Date");

                Map<String, Object> varMap = new HashMap<String, Object>();
                TableDefine tableDefine = new TableDefine();
                List<ColumnDefine> columnDefineList = new ArrayList<ColumnDefine>();
                varMap.put("tableDefine", tableDefine);
                varMap.put("columnDefineList", columnDefineList);
                ExcelUtil.importFile(templatePath, "table-import.xml", new FileInputStream(logFileDb.getFilePath() + logFileDb.getFileName()), varMap);

                // 建表
                StringBuffer sb = new StringBuffer();
                // 实体类
                StringBuffer esb = new StringBuffer();

                String pk = "";
                if (tableDefine != null && columnDefineList != null && columnDefineList.size() > 0) {
            /*List<String> pkList = new ArrayList<String>();
            if (!StringUtils.isEmpty(tableDefine.getPk())) {
                pkList = Arrays.asList(tableDefine.getPk().replaceAll(" ", "").toLowerCase().split(","));
            }*/

                    sb.append("create table " + tableDefine.getName().toLowerCase() + "\n");
                    sb.append("(\n");

                    esb.append("package " + tableDefine.getPackageName() + ";\n\n");
                    esb.append("import javax.persistence.Entity;\n");
                    esb.append("import javax.persistence.Id;\n");
                    esb.append("import javax.persistence.Table;\n");
                    esb.append("import java.io.Serializable;\n\n");

                    esb.append("@Entity\n");
                    esb.append("@Table(name = \"" + tableDefine.getName().toLowerCase() + "\")\n");
                    esb.append("public class " + CamelCaseUtils.toCapitalizeCamelCase(tableDefine.getName().toLowerCase()) + " implements Serializable {\n");
                    esb.append("\tprivate static final long serialVersionUID = 1L;\n\n");

                    for (int i=0; i<columnDefineList.size(); i++) {
                        ColumnDefine columnDefine = columnDefineList.get(i);
                        String type = columnDefine.getType();
                        sb.append("\t" + columnDefine.getName().toLowerCase() + " " + type.toLowerCase());
                        if (!StringUtils.isEmpty(columnDefine.getLength())) {
                            sb.append("(" + columnDefine.getLength() + (!StringUtils.isEmpty(columnDefine.getPrecision()) ? "," + columnDefine.getPrecision() : "")
                                    + (type.equalsIgnoreCase("varchar2") ? " char" : "") + ")");
                        }
                        if (!StringUtils.isEmpty(columnDefine.getPk()) || !StringUtils.isEmpty(columnDefine.getEmpty())) {
                            sb.append(" not null");
                        }
                        sb.append(",\n");

                        if (!StringUtils.isEmpty(columnDefine.getPk())) {
                            if (!StringUtils.isEmpty(pk)) {
                                pk += ",";
                            }
                            pk += columnDefine.getName();
                            esb.append("\t@Id\n");
                        }
                        String javaType = javaTypeMap.get(type);
                        if (javaType != null) {
                            if (javaType.equals("Integer") && !StringUtils.isEmpty(columnDefine.getPrecision())) {
                                javaType = "BigDecimal";
                            }
                            esb.append("\tprivate " + javaType + " " + CamelCaseUtils.toCamelCase(columnDefine.getName()) + ";\n");
                        }
                    }
                    if (!StringUtils.isEmpty(pk)) {
                        sb.append("\t" + "constraint "  + tableDefine.getName().toLowerCase() + "_pk primary key(" + pk.toLowerCase() + ")\n");
                    }
                    sb.append(");");

                    esb.append("}");
                }

                varMap = new HashMap<>();
                varMap.put("tableSql", sb.toString());
                varMap.put("tableEntity", esb.toString());
                return ExcelUtil.exportFile(templatePath, "table-export.xlsx", StringUtil.serial() + ".xlsx", varMap);
            }
        }
        return null;
    }

    @RequestMapping(value = "/encode", method = RequestMethod.GET)
    public String encode(String s) throws NoSuchAlgorithmException {
        return new BCryptPasswordEncoder().encode(EncryptUtil.MD5Encode(s));
    }

    /*@RequestMapping(value = "/message", method = RequestMethod.GET)
    public String message(@RequestParam(name = "hello") String s) {
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("date", DateUtil.getDateDate());
        messageMap.put("hello", s);
        sender.send(messageMap);
        return "success";
    }*/
}
