package d706;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConcatenatedWords {
	// 1.开始想把每个字符串拿出来，在set里去掉
	// 然后不断截取prefix，判断set是否包含prefix
	// 如果包含，截掉prefix，向下传递，当word长度为0返回true
	// 问题：1.字符串截取i应<=word.length 2.如果wordlist里有空字符串，应先判断如果word为空不search
	// 上个方法超时，
	//2.由后向前截取字符串，省略了字符串为空的判断
	// 问题：由后向前截取字符串for(int i = word.length - 1; i >= 0; i--)
	// 当wordlist有空字符串，会造成死循环，改为i>=1， 仍然超时
	//3.memorization记录一个词是否是合成，如果截取到剩下的词是合成，则本词为合成,超时
	//4.按长度排序,长度短的会被先被判断为是不是合成，超时
	public static List<String> findAllConcatenatedWordsInADict(String[] words) {
		List<String> res = new ArrayList<String>();
		if (words.length <= 2) {
			return res;
		}
		Set<String> set = new HashSet<String>();
		for (String word : words) {
			set.add(word);
		}
		Set<String> isCon = new HashSet<String>();
		Arrays.sort(words, new Comparator<String>(){
			public int compare(String s1, String s2){
				return s1.length() - s2.length();
			}
		});
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			Set<String> temp = new HashSet<String>(set);
			temp.remove(word);
			if (search(word, temp, isCon)) {
				res.add(word);
				isCon.add(word);
			}
		}
		return res;
	}

	public static boolean search(String word, Set<String> set, Set<String> isCon) {
		if(isCon.contains(word)){
			return true;
		}
		if (set.contains(word)) {
			return true;
		}
		// 开始截取字符串由前向后，写成了
		// for(int i = 0; i < word.length; i++) 应该是<=
		for (int i = word.length() - 1; i >= 1; i--) {
			String pre = word.substring(0, i);
			if (set.contains(pre) || isCon.contains(pre)) {
				if (search(word.substring(i), set, isCon)) {
					return true;
				}
			}
		}
		return false;
	}
	
	//solution 1
	//cache 中间结果到set,我的解法没有存中间结果
	public List<String> findAllConcatenatedWords2InADict(String[] words) {
	    List<String> list = new ArrayList<String>();
	    Set<String> set = new HashSet(Arrays.asList(words));

	    for(String word : words) {
	        set.remove(word);
	        if(dfs(word, set, "")) list.add(word);
	        set.add(word);
	    }
	    return list;
	}

	private boolean dfs(String word, Set<String> set, String previous) {
	    if(!previous.equals("")) set.add(previous);
	    if(set.contains(word)) return true;
	    
	    for(int i = 1; i < word.length(); i++) {
	        String prefix = word.substring(0,i);
	        if(set.contains(prefix) && 
	            dfs(word.substring(i,word.length()), set, previous+prefix)) {
	            return true;
	        }
	    }
	    return false;
	}
	//dp解法，set的写法减小搜索空间
	//dp判断, 139 wordbreak
	public List<String> findAllConcatenatedWordsInADict3(String[] words) {
        List<String> res = new ArrayList<String>();
		if (words.length <= 2) {
			return res;
		}
		Set<String> set = new HashSet<String>();
		Arrays.sort(words, new Comparator<String>(){
			public int compare(String s1, String s2){
				return s1.length() - s2.length();
			}
		});
        
		for (int i = 0; i < words.length; i++) {
			if(search(words[i], set)){
                res.add(words[i]);
            }
            set.add(words[i]);
		}
		return res;
    }
    
    public boolean search(String word, Set<String> set){
        if(set.isEmpty()){//注意
            return false;
        }
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;//声明注意
        for(int i = 1; i <= word.length(); i++){
            for(int j = 0; j < i; j++){
                if(!dp[j]){
                    continue;
                }
                if(set.contains(word.substring(j, i))){
                    dp[i] = true;
                    break;//退出
                }
            }
        }
        return dp[word.length()];
    }

	public static void main(String[] args) {
		String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
		String[] words2 = {"rfkqyuqfjkx","","vnrtysfrzrmzl","gfve","qfpd","lqdqrrcrwdnxeuo","q","klaitgdphcspij","hbsfyfv","adzpbfudkklrw","aozmixr","ife","feclhbvfuk","yeqfqojwtw","sileeztxwjl","ngbqqmbxqcqp","khhqr","dwfcayssyoqc","omwufbdfxu","zhift","kczvhsybloet","crfhpxprbsshsjxd","ilebxwbcto","yaxzfbjbkrxi","imqpzwmshlpj","ta","hbuxhwadlpto","eziwkmg","ovqzgdixrpddzp","c","wnqwqecyjyib","jy","mjfqwltvzk","tpvo","phckcyufdqml","lim","lfz","tgygdt","nhcvpf","fbrpzlk","shwywshtdgmb","bkkxcvg","monmwvytby","nuqhmfj","qtg","cwkuzyamnerp","fmwevhwlezo","ye","hbrcewjxvcezi","tiq","tfsrptug","iznorvonzjfea","gama","apwlmbzit","s","hzkosvn","nberblt","kggdgpljfisylt","mf","h","bljvkypcflsaqe","cijcyrgmqirz","iaxakholawoydvch","e","gttxwpuk","jf","xbrtspfttota","sngqvoijxuv","bztvaal","zxbshnrvbykjql","zz","mlvyoshiktodnsjj","qplci","lzqrxl","qxru","ygjtyzleizme","inx","lwhhjwsl","endjvxjyghrveu","phknqtsdtwxcktmw","wsdthzmlmbhjkm","u","pbqurqfxgqlojmws","mowsjvpvhznbsi","hdkbdxqg","ge","pzchrgef","ukmcowoe","nwhpiid","xdnnl","n","yjyssbsoc","cdzcuunkrf","uvouaghhcyvmlk","aajpfpyljt","jpyntsefxi","wjute","y","pbcnmhf","qmmidmvkn","xmywegmtuno","vuzygv","uxtrdsdfzfssmel","odjgdgzfmrazvnd","a","rdkugsbdpawxi","ivd","bbqeonycaegxfj","lrfkraoheucsvpi","eqrswgkaaaohxx","hqjtkqaqh","berbpmglbjipnuj","wogwczlkyrde","aqufowbig","snjniegvdvotu","ocedkt","bbufnxorixibbd","rzuqsyr","qghoy","evcuanuujszitaoa","wsx","glafbwzdd","znrvjqeyqi","npitruijvyllsi","objltu","ryp","nvybsfrxtlfmp","id","zoolzslgd","owijatklvjzscizr","upmsoxftumyxifyu","xucubv","fctkqlroq","zjv","wzi","ppvs","mflvioemycnphfjt","nwedtubynsb","repgcx","gsfomhvpmy","kdohe","tyycsibbeaxn","wjkfvabn","llkmagl","thkglauzgkeuly","paeurdvexqlw","akdt","ihmfrj","janxk","rqdll","cyhbsuxnlftmjc","yybwsjmajbwtuhkk","ovytgaufpjl","iwbnzhybsx","mumbh","jqmdabmyu","br","lwstjkoxbczkj","vhsgzvwiixxaob","fso","qnebmfl","ooetjiz","lq","msxphqdgz","mqhoggvrvjqrp","xbhkkfg","zxjegsyovdrmw","jav","mshoj","ax","biztkfomz","hujdmcyxdqteqja","gqgsomonv","reqqzzpw","lihdnvud","lznfhbaokxvce","fhxbldylqqewdnj","rlbskqgfvn","lfvobeyolyy","v","iwh","fpbuiujlolnjl","gvwxljbo","ypaotdzjxxrsc","mwrvel","umzpnoiei","ogwilaswn","yw","egdgye","hsrznlzrf","mwdgxaigmxpy","yaqgault","dtlg","cyvfiykmkllf","zxqyhvizqmamj","lvvgoifltzywueyp","abinmy","ppzaecvmx","qsmzc","iddymnl","uskihek","evxtehxtbthq","jvtfzddlgch","czohpyewf","ufzazyxtqxcu","brxpfymuvfvs","xrrcfuusicc","aqhlswbzievij","rv","udvmara","upityz","fecd","suxteeitxtg","dfuydrtbfypbn","cypqodxr","wikfuxwjht","jrliuaifpp","vkmxys","wvpfyfpkvgthq","rmajxis","jncxgviyu","av","nmhskodmidaj","lkfrimprrhen","uip","hstyopbvuiqc","p","vwduwmjpblqo","fnxwgqtvwztje","xwnbcuggl","iehimvoymyjasin","spsqiu","flhyfac","mqrbq","pstsxhplrrmbeddv","hnegtuxx","alsyxezjwtlwmxv","jtxytykkcku","bhhlovgcx","xhhivxnutkx","had","aysulvk","m","anhsyxli","jdkgfc","potn","lcibpxkidmwexp","gwoxjicdkv","tltienw","ngiutnuqbzi","o","tzlyb","vumnwehj","os","np","lhv","uzvgyeette","ipfvr","lpprjjalchhhcmh","k","pciulccqssaqgd","tp","dmzdzveslyjad","wtsbhgkd","eouxbldsxzm","vhtonlampljgzyve","xhnlcrldtfthul","xhflc","upgei","rlaks","yfqvnvtnqspyjbxr","phouoyhvls","voibuvbhhjcdflvl","rgorfbjrofokggaf","dqhqats","zchpicyuawpovm","yzwfor","koat","pybf","fhdzsbiyjld","gznfnqydisn","xz","po","tcjup","wygsnxk","kqlima","fgxnuohrnhg","publurhztntgmimc","zuufzphd","iucrmmmjqtcey","wnnbq","rghzyz","ukjqsjbmp","mdtrgv","vyeikgjdnml","kxwldnmi","apzuhsbssaxj","tkbkoljyodlipof","nkq","ktwtj","vgmkgjwle","t","agylw","vomtuy","jbtvitkqn","vtdxwrclpspcn","rdrls","yxfeoh","upj","myctacn","fdnor","ahqghzhoqprgkym","phiuvdv","jp","fdgpouzjwbq","hqoyefmugjvewhxu","qfzwuwe","fnsbijkeepyxry","oja","qthkcij","zpmqfbmnr","ybaibmzonzqlnmd","svo","gjftyfehik","jfrfgznuaytvaegm","aljhrx","odjq","ogwaxrssjxgvnka","zaqswwofedxj","lugpktauixp","dc","odknlbvxrs","jeobu","vqythyvzxbcgrlbg","hwc","erpbaxq","ujxcxck","rrklkb","wlrwyuy","zmg","yyhga","xwdbycdu","htedgvsrhchox","wr","suhesetv","jonqwhkwezjvjgg","sqqyrxtjkcalswq","hvyimhe","pjzdkmoue","zbphmgoxq","lbdlcumdgixjbcq","ztzdjqmadthtdmv","qcagsyqggcf","if","jpjxcjyi","chyicqibxdgkqtg","iwpdklhum","wljmg","micmun","npdbamofynykqv","ijsnfkpfy","lmq","oyjmeqvhcrvgm","mqopusqktdthpvz","fz","r","qbsqtipq","nxtsnason","xbpipyhh","topsuqomfjrd","islif","gbndakaq","bwnkxnwpzeoohlx","hrtbfnq","fguvomeepxoffg","mat","dzfpfnwbfuj","onlvy","cwcchvsasdylb","rxfcztzqopdi","ybrhodjn","oqkijy","ncvrjo","dphbfaal","xgtpdtkz","sebevsopjvciwljf","rcumyacqdapwczen","mabkapuoud","pbozezeygljfftvy","bvazmzbndl","vl","qiaixdtbhqvlzd","ffjfb","svthrfmkoxbho","cvet","ucgqyvopafyttrh","lbgihet","naiqyufxffdw","vruh","uz","ukffmudygjavem","dccamymhp","wofwgjkykm","fbuujzxhln","kmm","lzandlltowjpwsal","fapfvrmezbsjxs","wiw","sc","soqlh","hzaplclkwl","gcdqbcdwbwa","gadgt","pgowefka","juffuguqepwnfh","nbuinl","cpdxf","sox","fq","lfnrhgsxkhx","xrcorfygjxpi","mwtqjwbhgh","loc","fkglorkkvx","nlzdhucvayrz","azefobxutitrf","rlrstkcbtikklmh","ggk","sbphcejuylh","nraoenhd","zngyodiqlchxyycx","rrbhfwohfv","krzolrglgn","cpjesdzy","yoifoyg","hqqevqjugi","ahmv","xgaujnyclcjq","evhyfnlohavrj","byyvhgh","hyw","kedhvwy","ysljsqminajfipds","rglnpxfqwu","cibpynkxg","su","mbntqrlwyampdg","nig","ldhlhqdyjcfhu","jfymrbafmyoc","tyjmnhlfnrtz","dlazixtlxyvm","fbiguhsfuqo","rhymsno","rkbdlchs","ocbbwwd","astaiamnepwkya","mplirup","edkxjq","g","exlwulswtvot","tlnc","vnrrzerz","ygeraoozbtt","yyifkin","eo","ua","qgztvqdolf","rlzddjzcshvd","khxkdxflwxme","kk","zylbhoaac","cw","iizic","gcdxstpz","kjwdqeg","earjrncmmkdel","kbesuhquepj","nrzbllldgdmyrpgl","hllwnqozf","djpchowhwevbqvjj","zsmhylnjpktb","pxnktxkm","fxwiaqqb","qjwufmwresfsfaok","aa","d","iobioqm","svjgzk","khbzp","euexyudhrioi","yqsj","ngrwqpoh","rwuvd","eruffmlg","bxzovyew","faz","pmvfvyguqdi","jlxnoixsy","hyfrdngjf","ly","eibcapetpmeaid","tpnwwiif","pfgsp","kvhhwkzvtvlhhb","pjxurgqbtldims","rncplkeweoirje","akyprzzphew","wyvfpjyglzrmhfqp","ubheeqt","rmbxlcmn","taqakgim","apsbu","khwnykughmwrlk","vtdlzwpbhcsbvjno","tffmjggrmyil","schgwrrzt","mvndmua","nlwpw","glvbtkegzjs","piwllpgnlpcnezqs","xkelind","urtxsezrwz","zechoc","vfaimxrqnyiq","ybugjsblhzfravzn","btgcpqwovwp","zgxgodlhmix","sfzdknoxzassc","wgzvqkxuqrsqxs","dwneyqisozq","fg","vhfsf","uspujvqhydw","eadosqafyxbmzgr","tyff","blolplosqnfcwx","uwkl","puenodlvotb","iizudxqjvfnky","cjcywjkfvukvveq","jrxd","igwb","dftdyelydzyummmt","uvfmaicednym","oai","higfkfavgeemcgo","naefganqo","iqebfibigljbc","ulicojzjfrc","igxprunj","cymbrl","fqmwciqtynca","zjyagi","mzuejrttefhdwqc","zyiurxvf","wrjxffzbjexsh","wrxw","mhrbdxjwi","htknfa","wfrvxqdkhbwwef","vqsghhhutdget","cwupzrts","hbjnb","wpccoa","nx","howbzhaoscgyk","bilt","wqqatye","zceuuwg","jxzon","kkfj","bwsezd","ifdegsyjtswselk","xweimxlnzoh","tqthlftjblnpht","ww","ss","b","jmruuqscwjp","nxbk","wd","cqkrtbxgzg","xhppcjnq","cfq","tkkolzcfi","wblxki","ijeglxsvc","kcqjjwcwuhvzydm","gubqavlqffhrzz","hiwxrgftittd","caybc","ncsyjlzlxyyklc","poxcgnexmaajzuha","dhaccuualacyl","mtkewbprs","oncggqvr","sqqoffmwkplsgbrp","ioajuppvqluhbdet","dzwwzaelmo","afumtqugec","wglucmugwqi","zveswrjevfz","nxlbkak","pzcejvxzeoybb","fd","vewj","ivws","zjhudtpqsfc","zcmukotirrxx","zksmx","umofzhhowyftz","zbotrokaxaryxlk","ueolqk","dxmzhoq","zvu","cjl","esfmqgvxwfy","npbep","vbgjtbv","poeugoqynkbfiv","fewjjscjrei","yqssxzsydgllfzmo","urxkwcypctjkabi","wqtldwhjouas","tovdtkr","onzgeyddkqwuhnim","ffxviyvsktqrfa","qujhd","pvcz","hiyjlkxmeplnrvxg","hdykehkefp","vepcxhozpjxtreyn","liguhuxudbnh","f","ordxzm","klgohcmmbukz","yrmooliaobbnlap","dutnbetocxylcey","ywdsjegd","cr","blbxhjsgcuoxmqft","ngzdc","srfyjjumcbxole","dazwzwtdjoyuqeqj","xazjarqgfm","fxyfqbeoktcc","qrsjchxp","iltaqzawhgu","sgenjcfxr","yfikp","dvwhbyumthkiktb","walsx","jyajrkcvysicisab","brdeumb","tviihjwxdcz","dnrrgmem","ydgxlrjzucxyid","cdvdpvjlagwmg","ngnpxjkxims","gvyhnchlimsxc","w","jtizpezjl","qe","rjzv","vhnqvi","qm","iedzqswrsnfmnn","lt","utqfcqyrrwm","wtelvsqrru","fjwrhjcrtbcytn","qmqxceuohpiffaq","rmoybqjjgdyo","pmxttqftypfexlv","tg","qa","iqbqjlnpbf","kgaynkddbzllecd","tccvslp","curkxfoimnw","fvnyqkzlheruxr","iiygnzfov","coqs","oa","eiu","vzemmxtklis","lxu","nrwsjaxzwmh","tdayz","oxbbemejgosgcynf","ykbcn","hesvnctfvdsp","ku","rjhykpadahbhj","at","sxlngbtxmqr","wqrom","qzyabzrco","rbbyklndcqdj","cnsmgmwmpbgjq","krvnaf","qrwfajnfahyqocdb","fnlaozmff","vmoymbmytjvfcgt","cijyy","jdgwjbztl","swmalgbgpaplqgz","hfl","typttkrpfvx","tkzpzrscwbx","bwfqqvjcukjbsg","nxqmxr","x","eyavnz","il","dhthp","eyelg","npsoqsw","reogbmveofvusdsx","jvdrjkhxkq","qzjbrpljwuzpl","czqeevvbvcwh","vzuszqvhlmapty","yu","yldwwgezlqur","vorxwgdtgjilgydq","pknt","bgihl","ckorgrm","ixylxjmlfv","bpoaboylced","zea","igfagitkrext","ipvqq","dmoerc","oqxbypihdv","dtjrrkxro","rexuhucxpi","bvmuyarjwqpcoywa","qwdmfpwvamisns","bhopoqdsref","tmnm","cre","ktrniqwoofoeenbz","vlrfcsftapyujmw","updqikocrdyex","bcxw","eaum","oklsqebuzeziisw","fzgyhvnwjcns","dybjywyaodsyw","lmu","eocfru","ztlbggsuzctoc","ilfzpszgrgj","imqypqo","fump","sjvmsbrcfwretbie","oxpmplpcg","wmqigymr","qevdyd","gmuyytguexnyc","hwialkbjgzc","lmg","gijjy","lplrsxznfkoklxlv","xrbasbznvxas","twn","bhqultkyfq","saeq","xbuw","zd","kng","uoay","kfykd","armuwp","gtghfxf","gpucqwbihemixqmy","jedyedimaa","pbdrx","toxmxzimgfao","zlteob","adoshnx","ufgmypupei","rqyex","ljhqsaneicvaerqx","ng","sid","zagpiuiia","re","oadojxmvgqgdodw","jszyeruwnupqgmy","jxigaskpj","zpsbhgokwtfcisj","vep","ebwrcpafxzhb","gjykhz","mfomgxjphcscuxj","iwbdvusywqlsc","opvrnx","mkgiwfvqfkotpdz","inpobubzbvstk","vubuucilxyh","bci","dibmye","rlcnvnuuqfvhw","oorbyyiigppuft","swpksfdxicemjbf","goabwrqdoudf","yjutkeqakoarru","wuznnlyd","vfelxvtggkkk","mxlwbkbklbwfsvr","advraqovan","smkln","jxxvzdjlpyurxpj","ssebtpznwoytjefo","dynaiukctgrzjx","irzosjuncvh","hcnhhrajahitn","vwtifcoqepqyzwya","kddxywvgqxo","syxngevs","batvzmziq","mjewiyo","pzsupxoflq","byzhtvvpj","cqnlvlzr","akvmxzbaei","mwo","vg","ekfkuajjogbxhjii","isdbplotyak","jvkmxhtmyznha","lqjnqzrwrmgt","mbbhfli","bpeohsufree","ajrcsfogh","lucidbnlysamvy","tutjdfnvhahxy","urbrmmadea","hghv","acnjx","athltizloasimp","gu","rjfozvgmdakdhao","iephs","uztnpqhdl","rfuyp","crcszmgplszwfn","zihegt","xbspa","cjbmsamjyqqrasz","ghzlgnfoas","ljxl","cnumquohlcgt","jm","mfccj","hfedli","vtpieworwhyiucs","tdtuquartspkotm","pnkeluekvelj","ugrloq","zljmwt","fkyvqguqq","tpjidglpxqfxv","l","tvvimvroz","yy","opwyfovdz","pwlumocnyuoume","vjqpzkcfc","ihicd","dtttiixlhpikbv","goblttgvmndkqgg","gwsibcqahmyyeagk","prtvoju","lcblwidhjpu","kbu","pey","gkzrpc","bqajopjjlfthe","bc","lqs","zkndgojnjnxqsoqi","zyesldujjlp","drswybwlfyzph","xzluwbtmoxokk","bedrqfui","opajzeahv","lehdfnr","mnlpimduzgmwszc","velbhj","miwdn","wruqc","kscfodjxg","wcbm"};
		findAllConcatenatedWordsInADict(words2);
	}
}
