package com.banyechan.mysql_learn_demo.serviceImpl;


import com.banyechan.mysql_learn_demo.entity.StudentModel;
import com.banyechan.mysql_learn_demo.mapper.StudentModelMapper;
import com.banyechan.mysql_learn_demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    //400个name
    private String [] nameList ={"胡志和","李天瑜","王安琪","陈九昌","陈九雪","张正光","王正虎","胡志超","李媛","郭天营",
            "黄显玉","左太强","陈映芳","张正蕊","张文升","严世翔","石天玉","李华慧","姜世盈","朱显荣",
            "尹  俊"," 罗  坤","李福忠","鲁升升","赵平菊","李  倩","郑明健","黄光享","熊光美","查佳婷",
            "刘苗苗","杨东菊","周先明","字鑫宇"," 李  蕊","李  权","王顺超","张佳佳","石成妤","李天祥",
            "曾开智","朱文丽","李安强","李福有","周顺才","赵思杰","李天会","王先福","鲁诗娜","周孟婷",
            "张婷婷"," 李顺杰","查红英","陈世灏","龙先芹","邢承达","李春秋"," 罗先萍","石云专","李秋雨",
            "罗天兰","王君仝","徐娥升","李天星","李伟芳","罗天虹","石兆阳","李兴奇","李江","杨丽茱",
            "郭新城"," 王健康","王天敏","石宏涛","李树","石成廷","李明"," 石何月","周润兰","张宏杰",
            "曾强","刘家美","周新晨","毛彬彬","李娜","字文烨","王兴萍","杨梦雪"," 李云浩","李新花",
            "段家廷","周先云","李茂凡","杨国璨","何有维","柏光寒","姜文超","潘学志","杨丽珠","陶文俊",
            "李艳","李新月","董洋新","周顺利","字绍平","张露寒"," 石成友","陈含琪","李云芳","李文艳",
            "黄偲涵","杨斐然","李泽瑞","黄婧涵","周  戈","陈峻锋","欧隽辰","陈瑾瑜","胡语心","林炫宏",
            "刘晏铭","毛骏燃","陈思元","郑婉苗","陈欣阳","林炫哲","徐力为","江美琪","严仟芊","吴雨霏",
            "周洛汐","张哲恺","陈钰孜","吴雨晗","陈羽萱","黎曦文","高钰颖","李雅旗","曾铉淇","焦  耳",
            "陈铭枫","彭江壹维","杨明道","阳知远","郑妍霖","焦  朵","陈  喆","房思辰","闫昊阳","邱博闻",
            "何钧天","梅羽彤","彭天烁","邱雨晨","贾雨潼","韩卓轩","任珈希","隆曼汐","戴湘峻","吴梓松",
            "邹家宝","陈筱函","钟予航","庄子贺之","陈孜文","王文渊","林晓晴","丁和康","华逞毅","冯智洋",
            "朱一鸣","杨宝富","林俊宇","付墨白","张子涵","刘晟宇","范峻铭","黄睿卿","何浩钊","谢家瑜",
            "李业泰","丘安妮","王诗璇","李颜希","王梓铭","董静姝","李州涵","文汐羽","刘希言","王炜竣",
            "许宸瑞","周颖硕","林逸嘉","黄子杰","熊煜绮","吴腾允","谢思宇","陈海娜","周君涵","李昊成",
            "谭皓天","芮嘉祥","郑奕博","宗  澄","伍芊桦","刘胤凯","方子颖","陈弈曈","韩炜宸","李欣妮",
            "陈相钧","许量子","武美悦","唐新悦","潘子语","蔡语桐","欧昭熙","黄梓晨","杨子轩","符煜彬",
            "贺熠佳","杨朝鹏","曹卓夫","孙羿翯","冯文暄","陈紫瑜","尚子越","李敏希","冯小曦","成一菲",
            "查钧宝","陈泓宇","朱抒伦","唐浚哲","吉明溪","王菡朵","曾欣雨","陈思瀚","邓斯尹","伍彦臻",
            "吴俊毅","高嘉蔚","谢雨墨","杨会铭","王俊量","赵嘉琦","黄睦棋","张蕙","刘子今","郑宇欣",
            "刘子源","劳奕臻","唐佩珍","林语涵","张一行","杨涛诚","罗誉轩","郭子楚","吴俞炘","范一影",
            "李开发","邱埴桓","金泓宪","吴明蔚","林雨嫣","李婧妤","陈宁远","赵梓轩","文心语","张晨曦",
            "周泽锴","姜  军","高瑞琳","万一鹏","孙浩然","谢宛庭","陈楚恒","兰奕凡","陈安妮","叶瀚辰",
            "潘  盼","潘楚昀","周青雅","封泽域","樊骐辉","林  宸","颜智烨","张云清","李耀霆","王恩瑞",
            "陈国樊","乔昱茗","张晟龙","陈  晞","林睿珊","张雅妮","肖  淇","赵翊彤","胡焕宜","林子童",
            "邱梓怡","马馨彤","梁熙彤","冯天天","廖予豪","冯嘉峻","王景铄","陈麒宇","游子轩","李佳琳",
            "胡桐彤","孙俊哲","连允祺","夏誉馨","王婧琦","张艺轩","李羽宸","王子墨","涂晓莹","陈彦希",
            "陈嘉晟","崔颖颖","彭  格","杨厚智","梁逸晨","黄梓晴","潘子恩","薄  尧","施粤缤","刘梓怡",
            "向昱尧","乔若言","温予菲","霍文琰","杨子晨","陈子源","李昕沄","付昊霖","伍毅辰","朱益煊",
            "王志兵","单玉龙","王志林","刘旺林","静凯","师宪波","李冀康","王乐","房佳豪","王攀",
            "王昊宇","王跃康","刘仲浩","刘建辉","王明飞","边文峰","刘奥祥","王博凯","边豪杰","李嘉星",
            "李成强","刘江涛","王玉霞","王瑜帆","王岚岚","王丽丽","王思怡","王新新","杨之茵","王晨羽",
            "李嘉泽","谭静文","孙海华","韩艺博","夏鑫磊","李佳琪","孙心诺","朱美妍","邵子硕","赵中华",
            "郑胜豪","李紫涵","万广真","孙何宇","刘畅","张珂","王士奥","朱宇含","韩雨峥","王静雯",
            "崔永琪","刘洛菘","周博文","宋正妍","褚紫然","艾信成","秦浩哲","褚玉清","董若曦","熊莉文"
            };

    private String [] subjectArr = {"语文","代数","英语","美术"};
//    private String [] subjectArr = {"语文","代数","几何","英语","日语","韩语","生物","物理","化学","体育",
//                                    "历史","政治","地理","美术","音乐","围棋","象棋","书法","钢琴","篮球",
//                                    "土木","会计","交通","临床","机电"};

    @Autowired
    private StudentModelMapper studentMapper;

    @Override
    public StudentModel getByByPrimaryKey(Integer id) {

        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StudentModel> listStudent(StudentModel record) {
        return studentMapper.listByCondition(record);
    }

    @Override
    public boolean saveStudent(StudentModel record) {
        int flag = studentMapper.insertSelective(record);
        return flag > 0;
    }

    @Override
    public boolean updateStudent(StudentModel record) {
        int flag = studentMapper.updateByPrimaryKeySelective(record);
        return flag > 0;
    }

    @Override
    public boolean deleteStudent(Integer id) {
        int flag = studentMapper.deleteByPrimaryKey(id);
        return flag > 0;
    }

    @Override
    public List<StudentModel> listAllStudent() {
        return studentMapper.listStudent();
    }


    @Override
    public boolean batchAddStudent(int num) {
        if(num < 1 ){
            num = 1;
        }
        for(int i = 0;i < num ; i++){
            batchInsertTwo();
        }

        return true;
    }


    // 批量插入方法1
    private boolean batchInsertOne(){
        long start = System.currentTimeMillis();
        int number = 0;
        for (int i = 0; i < subjectArr.length; i++) {
            for (String tem : nameList){
                StudentModel temStudent = new StudentModel();
                temStudent.setSubject(subjectArr[i]);
                temStudent.setName(tem);
                int r = (int) (Math.random() * 70);
                temStudent.setAge(r != 0 ? r : 1);
                if(r%2 == 1){
                    temStudent.setSex("男");
                }else{
                    temStudent.setSex("女");
                }

                int score = (int) (Math.random() * 100);
                temStudent.setScore(score);
                int n = studentMapper.insertSelective(temStudent);

                if(n > 0){
                    number++;
                    System.out.println("新增第"+ number +"条记录,内容为" + temStudent.toString() );
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.println( );
        System.out.println("新增了一共"+ number +"名学生，用时为"+ (end - start)  );

        return number> 0;
    }

    // 批量插入方法2
    private boolean batchInsertTwo(){
        //数字自增 线程安全
        AtomicInteger atomicInteger = new AtomicInteger();
        int count = 0;
        long start = System.currentTimeMillis();
        List<StudentModel> studentList = new ArrayList<>();
        for(String temName : nameList){
            int r = (int) (Math.random() * 70);
            String sex = null;
            if(r%2 == 1){
                sex = "男";
            }else{
                sex = "女";
            }
            for (int i = 0; i < subjectArr.length; i++) {
                StudentModel temModel = new StudentModel();
                int id = atomicInteger.getAndIncrement();
                temModel.setId(id);
                temModel.setName(temName);
                temModel.setAge(r != 0 ? r : 1);
                temModel.setSex(sex);
                temModel.setSubject(subjectArr[i]);
                int score = (int) (Math.random() * 100);
                temModel.setScore(score);

                studentList.add(temModel);
                System.out.println(temModel.toString());
            }
            int n = studentMapper.batchAdd(studentList);
            count = count + n;
            studentList.clear();
        }


        long end = System.currentTimeMillis();
        System.out.println( );
        System.out.println("新增了一共"+ count +"名学生，用时为"+ (end - start)  );

        return count > 0;
    }

    // 批量插入方法3
    private boolean batchInsertThree(){
        long start = System.currentTimeMillis();
        List<StudentModel> studentList = new ArrayList<>();
        for (int i = 0; i < subjectArr.length; i++) {
            StudentModel temModel = new StudentModel();
            temModel.setSubject(subjectArr[i]);
            temModel.setName("tom");
            temModel.setAge(20);
            temModel.setSex("男");
            int score = (int) (Math.random() * 100);
            temModel.setScore(score);

            studentList.add(temModel);
        }
        int n = studentMapper.batchAdd(studentList);

        long end = System.currentTimeMillis();
        System.out.println( );
        System.out.println("新增了一共"+ n +"名学生，用时为"+ (end - start)  );

        return n > 0;
    }








    public static void main(String[] args) {
//        String a = "  韩迪  仲雨帆  靳子涵  贺梦琦  秦国庆  褚宏邦  赵  泳  张金虹  赵  灿 孙延美  孙紫嫣  周晓肖  郑子涵";
//        String [] arr = a.split("  ");
//        System.out.println("arr.length=" + arr.length);
//        StringBuilder sb = new StringBuilder();
//        int n = 0;
//        for(int i =0; i < arr.length ; i++){
//            if(n == 10){
//                System.out.println(sb.toString());
//                sb = new StringBuilder();
//                //System.out.println("=======================================================");
//                n = 0;
//            }else{
//                sb.append("\"").append(arr[i]).append("\",");
//                n++;
//            }
//
//
//        }

        //Rand_30();


//        String [] subjectArr = {"语文","代数","几何","英语","日语","韩语","生物","物理","化学","体育",
//                                   "历史","政治","地理","美术","音乐","围棋","象棋","书法","钢琴","篮球",
//                                   "土木","会计","交通","临床","机电"};
//        List<String> subjectList = new ArrayList<>();
//        for(String tem : subjectArr){
//            subjectList.add(tem);
//        }
//        if(subjectList.size() > 10){
//            int times = (int) Math.ceil(subjectList.size()/ 10);
//            log.info("---------time={}",times);
//            for(int i = 0;i<=times;i++){
//                log.info("------第{}次循环",i);
//                List<String> temList = subjectList.subList(i*10, Math.min((i+1)*10, subjectList.size()) );
//                log.info("=====temList={}",temList);
//            }
//
//        }

        log.info("===23 / 10={}",(23 / 10) );
        log.info("===23 / 10.0={}",(23 / 10.0) );
        log.info("===23.0 / 10={}",(23.0 / 10) );
        log.info("===23.0 / 10.0={}",(23.0 / 10.0) );
        log.info("===Math.ceil(23/ 10)={}",Math.ceil(23 / 10));
        log.info("===Math.ceil(23/ 10.0)={}",Math.ceil(23 / 10.0));
        log.info("===(int)Math.ceil(23/ 10)={}",(int)Math.ceil(23 / 10));


    }


    /**
     * 生成100以内的随机数 30次
     */
    public static void Rand_30() {
        for (int i = 0; i < 30; i++) {

            int r = (int) (Math.random() * 70);
            System.out.print(r + " ");

            if(r%2 == 1){
                System.out.print("男");
            }else{
                System.out.print("女");
            }
        }

    }


}
