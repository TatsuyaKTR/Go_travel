# 変更箇所
### controller/AccommodationController.java
- search()メソッド
    -　パラメータをlanguageIdをList<String>で取得するよう変更 
    - 全組み合わせ検索処理を追加
    - 最安値，最も古い日，最も新しい日を表示できる処理を追加
    - Viewの表示の際に宿IDの重複を削除する処理を追加
- detailメソッド
    -　ホテルの情報を取得
  　-  ホテルの部屋名とその日付のリストをMapで追加する処理を追加
  
### entity/Accommodation.java
- 宿のプランの中で最も古い日(oldDate)，最も新しい日のフィールド(newDate)と，それぞれgetter,setterを追記
### repository/AccommodationRepository.java
- 必要なJPAクエリを追加
- 言語検索の際にはfindByLanguageIn(List<String> languageList)のように取得するよう変更
### repository/PlanRepository.java
- 必要なJPAクエリを追加
## templates/search.html
- 対応言語のvaluesを'ja','en','cn'に変更
- 住所のvaluesを'北海道'のように日本語に変更
## templates/accommodation.html
- 必要なhtmlとタイムリーフを一部修正
## templates/accommodationInf.html
- 必要なhtmlとタイムリーフを追加
