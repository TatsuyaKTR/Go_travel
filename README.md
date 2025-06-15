# 変更箇所
### controller/AccommodationController.java
- search()メソッド
    -　パラメータをlanguageIdをList<String>で取得するよう変更 
    - 全組み合わせ検索処理を追加
    - 最安値，最も古い日，最も新しい日を表示できる処理を追加
    - Viewの表示の際に宿IDの重複を削除する処理を追加
### entity/Accommodation.java
- 宿のプランの中で最も古い日(oldDate)，最も新しい日のフィールド(newDate)と，それぞれgetter,setterを追記
### repository/AccommodationRepository.java
- 必要なJPAクエリを追加
- 言語検索の際にはfindByLanguageIn(List<String> languageList)のように取得するよう変更
### repository/PlanRepository.java
- 必要なJPAクエリを追加
